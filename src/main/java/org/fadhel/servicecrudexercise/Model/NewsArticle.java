package org.fadhel.servicecrudexercise.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message="ID field can't be null")
    private String id;

    @NotEmpty(message="Title field can't be empty")
    @Size(max=100)
    private String title;

    @NotEmpty(message="Author field can't be empty")
    @Size(min=5 ,max=20)
    private String author;

    @NotEmpty(message="Content field can't be empty")
    @Size(min=201)
    private String content;

    @NotEmpty(message="Content field can't be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message="Category field must be either politics, sports or technology only")
    private String category;

    @NotEmpty(message="image url field can't be empty")
    private String imageUrl;

    private boolean isPublished;

    private LocalDate  publishDate;
}
