package org.igorgvozdic.newsarticle.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.model.Category;

@XmlRootElement
public class ArticleDTO {

	private int id;
	
	private String title;
	
	private String author;
	
	private String shortDescription;
	
	private Category category;
	
	public ArticleDTO() {
		
	}

	public ArticleDTO(String title, String author, String shortDescription, Category category) {
		this.title = title;
		this.author = author;
		this.shortDescription = shortDescription;
		this.category = category;
	}
	
	public ArticleDTO(Article article) {
		this.title = article.getTitle();
		this.author = article.getAuthor();
		this.shortDescription = article.getShortDescription();
		this.category = article.getCategory();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public ArticleDTO asArticleDTO(Article article) {
		
		ArticleDTO articleDTO = new ArticleDTO();
		
		articleDTO.setAuthor(article.getAuthor());
		articleDTO.setTitle(article.getTitle());
		articleDTO.setShortDescription(article.getShortDescription());
		articleDTO.setCategory(article.getCategory());
		
		return articleDTO;
	}
	
	public Article asArticle() {
		return new Article(this);
	}
}
	