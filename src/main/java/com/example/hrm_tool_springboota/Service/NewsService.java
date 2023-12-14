package com.example.hrm_tool_springboota.Service;

import com.example.hrm_tool_springboota.Modal.News;

import java.util.List;

public interface NewsService {
    void createNews(News news);

    List<News> getNews();
}
