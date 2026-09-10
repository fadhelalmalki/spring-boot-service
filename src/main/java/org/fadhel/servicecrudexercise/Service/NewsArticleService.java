package org.fadhel.servicecrudexercise.Service;


import org.fadhel.servicecrudexercise.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles =  new ArrayList<>();

    // to get all news article objects
    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    // to add a news article
    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    // to update a news article
    public boolean updateNewsArticle(String id, NewsArticle updatedNewsArticle){
        for(NewsArticle newsArticle : newsArticles){
            if(newsArticle.getId().equals(id)){
                newsArticle.setTitle(updatedNewsArticle.getTitle());
                newsArticle.setAuthor(updatedNewsArticle.getAuthor());
                newsArticle.setContent(updatedNewsArticle.getContent());
                newsArticle.setCategory(updatedNewsArticle.getCategory());
                newsArticle.setImageUrl(updatedNewsArticle.getImageUrl());
                newsArticle.setPublished(updatedNewsArticle.isPublished());
                newsArticle.setPublishDate(updatedNewsArticle.getPublishDate());
                return true;
            }
        }
        return false;
    }

    // to delete a news article
    public boolean deleteNewsArticle(String id){
        for(NewsArticle newsArticle : newsArticles){
            if(newsArticle.getId().equals(id)){
                newsArticles.remove(newsArticle);
                return true;
            }
        }
        return false;
    }

    // to publish news articles
    public boolean publishNewsArticles(){

        if(newsArticles.isEmpty()){
            return false;
        }

        for(NewsArticle newsArticle : newsArticles){
            if(!newsArticle.isPublished()){
                newsArticle.setPublished(true);
                newsArticle.setPublishDate(LocalDate.now());
            }
        }
        return true;
    }

    // to get all published news articles
    public ArrayList<NewsArticle> getPublishedNewsArticles(){

        ArrayList<NewsArticle> publishedNewsArticles = new ArrayList<>();

        if(newsArticles.isEmpty()){
            return publishedNewsArticles;
        }

        for(NewsArticle newsArticle : newsArticles){
            if(newsArticle.isPublished()){
                publishedNewsArticles.add(newsArticle);
            }
        }

        return publishedNewsArticles;
    }

    // to get all news articles by category
    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category){

        ArrayList<NewsArticle> searchedNewsArticles = new ArrayList<>();

        if(newsArticles.isEmpty()){
            return searchedNewsArticles;
        }

        for(NewsArticle newsArticle : newsArticles){
            if(newsArticle.getCategory().equals(category)){
                searchedNewsArticles.add(newsArticle);
            }
        }
        return searchedNewsArticles;
    }
}
