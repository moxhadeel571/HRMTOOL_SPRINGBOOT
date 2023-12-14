package com.example.hrm_tool_springboota.Implementation;

import com.example.hrm_tool_springboota.Modal.News;
import com.example.hrm_tool_springboota.Repository.NewsRepository;
import com.example.hrm_tool_springboota.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsImplementation implements NewsService {
    private NewsRepository newsRepository;
@Autowired
    public NewsImplementation(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void createNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public List<News> getNews() {
    List<News> newsList = newsRepository.findAll();
        return newsList;
    }
}
