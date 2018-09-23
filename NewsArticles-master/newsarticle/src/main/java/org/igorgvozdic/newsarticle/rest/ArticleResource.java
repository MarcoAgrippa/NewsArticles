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
import javax.ws.rs.QueryParam;
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
	
	@GET
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)	
	public Result queryArticles(@QueryParam("title") String title,
								@QueryParam("author") String author,
								@QueryParam("shortdescription") String shortDescription,
								@QueryParam("date") String date,
								@QueryParam("all") String queryAll) {
		if (title != null && title.length() > 0) {
			return getArticlesByTitle(title);
		}
		
		if(author != null && author.length() > 0) {
			return getArticlesByAuthor(author);
		}
		
		if (shortDescription !=null && shortDescription.length() > 0 ) {
			return getArticlesByShortDescription(shortDescription);
		}
		
		if (date != null && date.length() > 0) {
			return getArticleByDate(date);
		}
		
		if (queryAll != null && queryAll.length() > 0) {
			return searchAll(queryAll);
		}
		
		return new Result(CustomException.NO_ARTICLE_FOUND);
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Result addArticle(ArticleDTO articleDTO) {
		
		Article article = articleDTO.asArticle();
		
		try {
			articleService.addArticle(article);
			return new Result(CustomException.OK);
			
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
	
	
	public Result getArticlesByTitle(String title) {
		
			try {
				List<ArticleDTO> articleDTOs = articleService.searchByTitle(title);
				
				return new Result(CustomException.OK, articleDTOs);
			} catch (ArticleException e) {
				throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
			}
	}
	
	
	public Result getArticlesByAuthor(String author) {
		
			try {
				List<ArticleDTO> articleDTOs = articleService.searchByAuthor(author);
				
				return new Result(CustomException.OK, articleDTOs);
			} catch (ArticleException e) {
				throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
			}
	}
	
	public Result getArticlesByShortDescription(String shortDescription) {
	
			try {
				List<ArticleDTO> articleDTOs = articleService.searchByShortDescription(shortDescription);
				return new Result(CustomException.OK, articleDTOs);
				
			} catch (ArticleException e) {
				throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
			}
		
	}
	
	public Result getArticleByDate(String date) {
		try {
			List<ArticleDTO> articleDTOs = articleService.searchArticleByDate(date);
			return new Result(CustomException.OK, articleDTOs);
			
		}catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
		}
	}
	
	public Result searchAll(String queryString) {
		
		try {
			List<ArticleDTO> articleDTOs = articleService.searchAll(queryString);
			
			return new Result(CustomException.OK, articleDTOs);
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
		}
	
}
	
	
}
