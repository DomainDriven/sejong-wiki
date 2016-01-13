package io.github.devsejong.wiki.core;

import io.github.devsejong.wiki.parser.DocType;
import io.github.devsejong.wiki.parser.ParserService;
import io.github.devsejong.wiki.repository.RepositoryService;
import org.eclipse.jgit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WikiController {

    @Autowired
    RepositoryService repoService;

    @Autowired
    ParserService parserService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/w/**", method = GET)
    public String viewWikiPage(HttpServletRequest request, Model model) throws IOException {
        String currentPath = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String path = currentPath.substring(currentPath.indexOf("/w/") + 3);

        Path filePath = repoService.getFilePath(path);
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        String text = StringUtils.join(lines, "\n");

        String html = parserService.parse(DocType.MARKDOWN, text.toString());
        model.addAttribute("html", html);

        return "wiki";
    }
}
