package org.igorgvozdic.newsarticle.bussineslogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.igorgvozdic.newsarticle.dao.ArticleDAO;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;

@Stateless
public class ArticleService {

	@EJB
	private ArticleDAO articleDAO;
	
	public String addArticle(Article article) {
		
		try {
			validateArticle(article);
			
		} catch (ArticleException e) {
			e.getStatusCode();
			e.getStatusMessage();
		}
		
		boolean message = articleDAO.saveArticle(article);
		
		if (message) {
			return "Ok";
		}else {
			return "Error";
		}
	}
	
	public List<ArticleDTO> getAllArticles() {
		
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		
		List<Article> articles = articleDAO.getAllArticles();
		
		for(Article article : articles) {
			articleDTOs.add(article.asFullArticleDTO());
			
		}
		return articleDTOs;
	}
	
	
	public Article getArticle(int id) {
		return articleDAO.getArticle(id);
	}
	
	
	public Article getArticle(String title) {
		return articleDAO.getArticle(title);
	}
	

	public Article editArticle(int id, Article article) {
		
		Article article1 = articleDAO.getArticle(id);
		
		if (article1 == null) {
			throw new ArticleException(CustomException.ARTICLE_NOT_FOUND);
		}
		
		return articleDAO.editArticle(id, article);
		
	}
	
	public String deleteArticle(int id) {
		
		Article article = articleDAO.getArticle(id);
		
		if (article != null) {
			articleDAO.deleteArticle(id);
			
			return "Deletion sucessful";
		}
		
		return "Error deleting article with " + id;
		
	}
	
	
	public void validateArticle(Article article){
		
		if (article != null) {
			if (article.getTitle().length() < 1 && article.getTitle().length() > 100) {
				
				throw  new ArticleException(CustomException.INVALID_TITLE);
			}
			
			if (article.getAuthor().length() < 1 && article.getAuthor().length() > 30 ) {
				throw new ArticleException(CustomException.INVALID_AUTHOR);
			}
			
			if (article.getShortDescription().length() < 5 && article.getShortDescription().length() > 200) {
				throw new ArticleException(CustomException.INVALID_SHORT_DESCRIPTION);
			}
			
			if (article.getCategory() == null) {
				throw new ArticleException(CustomException.INVALID_CATEGORY);
			}
		}
		
	}
	
	public List<ArticleDTO> searchByTitle(String title) {
		
		List<Article> articles = articleDAO.searchByTitle(title);
		
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		
		for(Article a : articles) {
			articleDTOs.add(a.asFullArticleDTO());
		}
		
		if (articleDTOs != null) {
			return articleDTOs;
		}
		
		return null;
	}
	
		public List<ArticleDTO> searchByAuthor(String author) {
		
		List<Article> articles = articleDAO.searchByAuthor(author);
		
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		
		for(Article a : articles) {
			articleDTOs.add(a.asFullArticleDTO());
		}
		
		if (articleDTOs != null) {
			return articleDTOs;
		}
		
		return null;
	}
		
		public List<ArticleDTO> searchByShortDescription(String shortDescription) {
			
			List<Article> articles = articleDAO.searchByTitle(shortDescription);
			
			List<ArticleDTO> articleDTOs = new ArrayList<>();
			
			for(Article a : articles) {
				articleDTOs.add(a.asFullArticleDTO());
			}
			
			if (articleDTOs != null) {
				return articleDTOs;
			}
			
			return null;
		}
		
		public List<ArticleDTO> searchAll(String query){
			
			List<Article> articlesFoundByTitle = articleDAO.searchByTitle(query);
			List<Article> articlesFoundByAuthor = articleDAO.searchByAuthor(query);
			List<Article> articlesFoundByShortDescription = articleDAO.searchByShortDescription(query);
			
			List<ArticleDTO> articleDTOs = new ArrayList<>();
			
			for(Article a : articlesFoundByTitle) {
				articleDTOs.add(a.asFullArticleDTO());
			}
			for(Article a : articlesFoundByAuthor) {
				articleDTOs.add(a.asFullArticleDTO());
			}	
			for(Article a : articlesFoundByShortDescription) {
				articleDTOs.add(a.asFullArticleDTO());
			}
			
			return articleDTOs;
		}
		
		
	
	
}
