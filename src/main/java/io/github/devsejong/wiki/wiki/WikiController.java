package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.document.DirectoryContent;
import io.github.devsejong.wiki.document.DocFileServiceException.DocumentNotFoundException;
import io.github.devsejong.wiki.document.Document;
import io.github.devsejong.wiki.parser.CoreParser;
import io.github.devsejong.wiki.parser.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WikiController {

    @Autowired
    WikiService wikiService;

    @RequestMapping("/")
    public String index() {
        // TODO 메인화면은 꾸밀 수 있어야 한다.
        return "index";
    }

    @RequestMapping("/config")
    public String config() {
        // 설정화면으로 진입한다.
        return "config";
    }

    @RequestMapping(value = "/w/**", method = RequestMethod.GET)
    public String viewWikiDoc(HttpServletRequest req, Model model) {
        String path = getPathFromUrl(req);

        Document document = wikiService.getDocument(path);
        model.addAttribute("html", wikiService.parse(document));
        model.addAttribute("title", getTitle(path));

        return "wiki/view";
    }

    // 문서가 없을 경우에 대한 처리..
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DocumentNotFoundException.class)
    public String documentNotExist(Model model){
        model.addAttribute("html", "문서 추가 기능은 작업 중.");
        model.addAttribute("title", "문서를 찾지 못하였습니다.");

        return "wiki/view";
    }

    //페이지 수정
    @RequestMapping(value = "/w/**", params = "mode=edit", method = RequestMethod.GET)
    public String editWikiDoc(HttpServletRequest req, Model model) {
        String path = getPathFromUrl(req);

        Document document = wikiService.getDocument(path);
        model.addAttribute("path", path);
        model.addAttribute("body", document.getBody());

        return "wiki/edit";
    }

    @RequestMapping(value = "/w/**", method = RequestMethod.POST)
    public String saveWikiDoc(HttpServletRequest req, String body, String comment) {
        String path = getPathFromUrl(req);
        wikiService.save(path, body, comment);

        return "redirect:/w/" + path;
    }

    // 폴더구조를 가져온다.
    @RequestMapping(value = "/wiki/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<DirectoryContent> getCategoryTree(@RequestParam(required = false, defaultValue = "") String node) {
        return wikiService.getDirContents(node);
    }

    @RequestMapping(value = "/wiki/parsing")
    @ResponseBody
    public String getParsedHtml(@RequestParam String body, @RequestParam String docType) {
        DocumentType type = CoreParser.getDocType(docType);
        return CoreParser.parse(type, body);
    }

    private String getPathFromUrl(HttpServletRequest req) {
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        path = path.replace("/w/", "");
        return path;
    }

    private String getTitle(String path) {
        String[] splittedPath = path.split("/");
        return splittedPath[splittedPath.length - 1];
    }
}
