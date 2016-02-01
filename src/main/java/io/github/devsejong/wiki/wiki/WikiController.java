package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.docfile.DirectoryContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/w/{path:.*}", method = RequestMethod.GET)
    public String viewWikiDoc(Model model, @PathVariable String path) {
        String parsedHtml = wikiService.readFileAndParse(path);
        model.addAttribute("html", parsedHtml);
        String[] splittedPath = path.split("/");
        model.addAttribute("title", splittedPath[splittedPath.length - 1]);

        return "wiki";
    }

    // 폴더구조를 가져온다.
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<DirectoryContent> viewWikiPage(@RequestParam String path) {
        return wikiService.getDirContents(path);
    }

}
