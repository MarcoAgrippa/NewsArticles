package org.igorgvozdic.newsarticle.soap;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService
public class ArticleSOAP {

	@EJB
	private ArticleService articleService;
	
	private Logger logger = LoggerFactory.getLogger(ArticleSOAP.class);
	
	public Result addArticle(ArticleDTO articleDTO) {
		
		Article article = articleDTO.asArticle();
		
		try {
			String resultString = articleService.addArticle(article);
			
			return new Result(resultString, articleDTO);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_CREATING_ARTICLE_IN_WEB_SERVICE);
		
		}
	}
	
	
	public Result getArticle(int id) {
		try {
			Article article = articleService.getArticle(id);
			ArticleDTO articleDTO = article.asFullArticleDTO();
			
			return new Result(CustomException.OK, articleDTO);
		}catch (ArticleException e) {
			throw new ArticleException(CustomException.ARTICLE_NOT_FOUND);
		}
	}
	
	public Result getAllArticles() {
		try {
			List<ArticleDTO> articles = articleService.getAllArticles();
			return new Result(CustomException.OK, articles);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLES_IN_DATABASE);
		}
		
	}
	
	public Result editArticle(int id, ArticleDTO articleDTO) {
		
		try {
			Article article = articleDTO.asArticle();
			logger.info(article.toString());
			
			Article article2 = articleService.editArticle(id, article);
			
			ArticleDTO articleDTO2 = article2.asFullArticleDTO();
			return new Result(CustomException.OK, articleDTO2);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_EDITING_ARTICLE_IN_WEB_SERVICE);
		}
		
	}
	

	public Result deleteArticle(int id) {
		
		try {
			String resultMessage = articleService.deleteArticle(id);
			return new Result(resultMessage);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.ERROR_DELETING_ADRTICLE_IN_WEB_SERVICE);
		}	
	}
	
	public Result findArticlesByTitle(String title) {
		try {
			List<ArticleDTO> articles = articleService.searchByTitle(title);
			return new Result(CustomException.OK, articles);
			
		}catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_TITLE);
		}
	}
	
	public Result findArticlesByAuthor(String author) {
		try {
			List<ArticleDTO> articles = articleService.searchByAuthor(author);
			return new Result(CustomException.OK, articles);
			
		}catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_TITLE);
		}
	}
	
	public Result findArticleByShortDescription(String shortDescription) {
		
		try {
			List<ArticleDTO> articleDTOs = articleService.searchByShortDescription(shortDescription);
			
			return new Result(CustomException.OK, articleDTOs);
			
		} catch (ArticleException e) {
			throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_DESCRIPTION);
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
