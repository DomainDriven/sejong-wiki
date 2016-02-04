package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.document.DirectoryContent;
import io.github.devsejong.wiki.document.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String viewWikiDoc(Model model, HttpServletRequest req) {
        String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        path = path.replace("/w/", "");

        try{
            String parsedHtml = wikiService.readFileAndParse(path);
            model.addAttribute("html", parsedHtml);
            String[] splittedPath = path.split("/");
            model.addAttribute("title", splittedPath[splittedPath.length - 1]);
        }
        // 문서가 없을 경우에 대한 처리..
        catch(DocumentNotFoundException e){
            model.addAttribute("html", "<h1>문서를 찾지 못했습니다.</h1>");
            model.addAttribute("title", "404..");
        }

        return "wiki";
    }

    // 폴더구조를 가져온다.
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<DirectoryContent> viewWikiPage(@RequestParam String path) {
        return wikiService.getDirContents(path);
    }

}
