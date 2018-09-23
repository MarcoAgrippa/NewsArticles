package org.igorgvozdic.newsarticle.web;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;

@Named
@RequestScoped
public class CreateArticleBean {
	
	@EJB
	private ArticleService aService;
	
	private ArticleDTO articleDTO;
	
	
	@PostConstruct
	public void init() {
		articleDTO = new ArticleDTO();
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}
	

	public String createArticle() {
		aService.addArticle(articleDTO.asArticle());
		articleDTO = new ArticleDTO();
		return "";
	}
	
}
