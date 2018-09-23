package org.igorgvozdic.newsarticle.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;

@Named
@RequestScoped
public class ArticleListBean {

	@EJB
	private ArticleService aService;
	
	private int id; 
	
	private ArticleDTO articleDTO;
	 
	private String query;
	
	private List<ArticleDTO> articles = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		articles = aService.getAllArticles();
		articleDTO = new ArticleDTO();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}
	
	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
	
	public String deleteArticle() {
		aService.deleteArticle(id);
		return "";
	}
	
	public String editArticle() {
		aService.editArticle(id, articleDTO.asArticle());
		articleDTO = new ArticleDTO();
		
		return "";
	}
	
	public String searchArticle() {
		articles = aService.searchAll(query);
		return "";
	}


}
