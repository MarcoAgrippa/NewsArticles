package org.igorgvozdic.newsarticle.model;

import org.igorgvozdic.newsarticle.dto.ArticleDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private String author;

    private String shortDescription;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate localDate = LocalDate.now();

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();


    public Article() {

    }

    public Article(int id, String title, String author, String shortDescription, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.shortDescription = shortDescription;
        this.category = category;
        this.localDate = LocalDate.now();
    }

    public Article(String title, String author, String shortDescription, Category category, LocalDate localDate, List<Comment> comments) {
        this.title = title;
        this.author = author;
        this.shortDescription = shortDescription;
        this.category = category;
        this.localDate = localDate;
        this.comments = comments;
    }

    public Article(ArticleDTO articleDTO) {
        this.author = articleDTO.getAuthor();
        this.title = articleDTO.getTitle();
        this.shortDescription = articleDTO.getShortDescription();
        this.category = articleDTO.getCategory();
        this.localDate = localDate.now();
    }

    public static LocalDate convertStringToLocalDate(String stringLocalDate) {

        if (stringLocalDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate parseDate = LocalDate.parse(stringLocalDate, formatter);
            return parseDate;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ArticleDTO asFullArticleDTO(ArticleDTO articleDTO) {
        return new ArticleDTO(this);
    }

    public ArticleDTO asFullArticleDTO() {

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId(this.id);
        articleDTO.setAuthor(this.author);
        articleDTO.setTitle(this.title);
        articleDTO.setShortDescription(this.shortDescription);
        articleDTO.setCategory(this.category);
        articleDTO.setLocalDate(this.localDate);

        return articleDTO;
    }

    public String convertLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String stringDate = localDate.format(formatter);
        return stringDate;
    }


    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", author=" + author + ", shortDescription="
                + shortDescription + ", category=" + category + " localDate =" + localDate + "]";
    }

}
