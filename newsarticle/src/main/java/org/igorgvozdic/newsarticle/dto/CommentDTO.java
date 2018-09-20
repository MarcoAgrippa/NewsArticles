package org.igorgvozdic.newsarticle.dto;


import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.model.Comment;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentDTO {

    private int id;

    private String title;

    private String author;

    private String content;

    private Article article;

    public CommentDTO() {

    }

    public CommentDTO(String title, String author, String conntent) {
        this.title = title;
        this.author = author;
        this.content = conntent;
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

    public String getContent() {
        return content;
    }

    public void setContent(String conntent) {
        this.content = conntent;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public CommentDTO asCommentDTO(Comment comment) {

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setTitle(comment.getTitle());
        commentDTO.setContent(comment.getContent());
        commentDTO.setArticle(comment.getArticle());

        return commentDTO;
    }

    public Comment asCommentDTO() {
        return new Comment(this);
    }
}
