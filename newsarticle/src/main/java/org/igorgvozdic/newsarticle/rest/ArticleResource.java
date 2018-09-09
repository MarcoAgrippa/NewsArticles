package org.igorgvozdic.newsarticle.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.result.Result;

@Path("articles")
public class ArticleResource {

	@EJB
	private ArticleService articleService;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Result getAllArticles() {
		try {
			List<ArticleDTO> articleDTOs = articleService.getAllArticles();
			
			return new Result(CustomException.OK, articleDTOs);
		} catch (ArithmeticException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Result getArticleById(@PathParam("id") int id) {
		try {
			Article article = articleService.getArticle(id);
			ArticleDTO articleDTO = article.asFullArticleDTO();
			
			return new Result(CustomException.OK, articleDTO);
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ARTICLE_NOT_FOUND);
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result addArticle(ArticleDTO articleDTO) {
		
		Article article = articleDTO.asArticle();
		
		try {
			String responseMessage = articleService.addArticle(article);
			return new Result(responseMessage);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_CREATING_ARTICLE_IN_WEB_SERVICE);
		}
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result editArticle(@PathParam("id") int id, ArticleDTO articleDTO) {
		
		try {
			Article article = articleDTO.asArticle();
			Article editedArticle = articleService.editArticle(id, article);
			
			return new Result(CustomException.OK, editedArticle.asFullArticleDTO());
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_EDITING_ARTICLE_IN_WEB_SERVICE);
		}
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Result deleteArticle(@PathParam("id") int id) {
		try {
			articleService.deleteArticle(id);
			
			return new Result(CustomException.OK);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_DELETING_ADRTICLE_IN_WEB_SERVICE);
		}
	}
	
	
	
}
