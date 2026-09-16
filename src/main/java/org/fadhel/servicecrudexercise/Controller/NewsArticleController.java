package org.fadhel.servicecrudexercise.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.servicecrudexercise.Api.ApiResponse;
import org.fadhel.servicecrudexercise.Model.NewsArticle;
import org.fadhel.servicecrudexercise.Service.NewsArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/newsarticle")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    // to get all news article objects
    @GetMapping("/get")
    public ResponseEntity<?> getNewsArticles() {

        ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();

        return ResponseEntity.status(200).body(newsArticles);
    }

    // to add a news article
    @PostMapping("/add")
    public ResponseEntity<?> addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addNewsArticle(newsArticle);

        return ResponseEntity.status(200).body(new ApiResponse("News article added successfully"));
    }

    // to update a news article
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewsArticle(@PathVariable String id,
                                               @RequestBody @Valid NewsArticle updatedNewsArticle, Errors errors) {

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = newsArticleService.updateNewsArticle(id, updatedNewsArticle);

        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("News article updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("News article could not be updated"));
    }

    // to delete a news article
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewsArticle(@PathVariable String id) {

        boolean isDeleted = newsArticleService.deleteNewsArticle(id);

        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("News article deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("News article could not be deleted"));
    }

    // to publish news articles
    @PutMapping("/publish")
    public ResponseEntity<?> publishNewsArticles() {

        boolean isPublished =  newsArticleService.publishNewsArticles();

        if(isPublished == true){
            return ResponseEntity.status(200).body(new ApiResponse("News article published successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("No news articles found in the list to publish"));
    }

    // to get all published news articles
    @GetMapping("/get-published-articles")
    public ResponseEntity<?> getPublishedNewsArticles() {

        ArrayList<NewsArticle> publishedNewsArticles = newsArticleService.getPublishedNewsArticles();

        if(publishedNewsArticles.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no published news articles"));
        }

        return ResponseEntity.status(200).body(publishedNewsArticles);
    }

    // to get all news articles by category
    @GetMapping("/get-articles-by-category/{category}")
    public ResponseEntity<?> getNewsArticlesByCategory(@PathVariable String category){

        ArrayList<NewsArticle> searchedNewsArticles = newsArticleService.getNewsArticlesByCategory(category);

        if(searchedNewsArticles.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no news articles found"));
        }

        return ResponseEntity.status(200).body(searchedNewsArticles);
    }

}
