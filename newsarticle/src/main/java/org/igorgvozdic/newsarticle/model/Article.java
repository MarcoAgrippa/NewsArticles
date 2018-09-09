package org.igorgvozdic.newsarticle.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.igorgvozdic.newsarticle.dto.ArticleDTO;

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
	
	public Article() {
		
	}
	
	public Article(int id, String title, String author, String shortDescription, Category category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.shortDescription = shortDescription;
		this.category = category;
	}
	
	public Article(ArticleDTO articleDTO) {
		this.author = articleDTO.getAuthor();
		this.title = articleDTO.getTitle();
		this.shortDescription = articleDTO.getShortDescription();
		this.category = articleDTO.getCategory();
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
	
	public ArticleDTO asFullArticleDTO(ArticleDTO articleDTO) {
		return new ArticleDTO(this);
	}
	
	public ArticleDTO asFullArticleDTO(){
		
		ArticleDTO articleDTO = new ArticleDTO();
		
		articleDTO.setId(this.id);
		articleDTO.setAuthor(this.author);
		articleDTO.setTitle(this.title);
		articleDTO.setShortDescription(this.shortDescription);
		articleDTO.setCategory(this.category);
		
		return articleDTO;
	}
	
	public void update(ArticleDTO articleDTO) {
		this.title = articleDTO.getTitle();
		this.author = articleDTO.getAuthor();
		this.shortDescription = articleDTO.getShortDescription();
		this.category = articleDTO.getCategory();
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", shortDescription="
				+ shortDescription + ", category=" + category + "]";
	}
	
}
