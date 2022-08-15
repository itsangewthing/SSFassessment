package vttp2022.NUSISS.ssfAssessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.NUSISS.ssfAssessment.models.News;
import vttp2022.NUSISS.ssfAssessment.services.NewsService;

@Controller
@RequestMapping(path="/")
//CONTROLLER'S CONTENT-TYPE: ALWAYS GENERATE TEXT/HTML
public class NewsController {

    @Autowired
    private NewsService newsSvc;


    ///// generate VIEW to GET the latest news
    @GetMapping(path="/")
    public String getNews(Model model, @RequestParam String feeds, String categories){

            //String user = form.getFirst("user");
            List<News> list = newsSvc.getArticles(feeds, categories);
            System.out.printf(">>> newsList: %s", list);

            model.addAttribute("newsList", list);
            return feeds;
        
    }

    ////////

    }
    


