package io.github.devsejong.wiki.wiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public String viewWikiPage(Model model, @PathVariable String path) throws IOException {
        String parsedHtml = wikiService.readFileAndParse(path);
        model.addAttribute("html", parsedHtml);
        return "wiki";
    }

    private String getPathFromRequest(HttpServletRequest request) {
        String currentPath = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE
        );
        return currentPath.substring(currentPath.indexOf("/w/") + 3);
    }
}
