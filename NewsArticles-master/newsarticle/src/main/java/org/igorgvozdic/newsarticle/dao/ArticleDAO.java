package org.igorgvozdic.newsarticle.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ArticleDAO {

	@PersistenceContext
	private EntityManager eManager;
	
	private final Logger logger = LoggerFactory.getLogger(ArticleDAO.class);
	
	public boolean saveArticle(Article article) {
		
		if (article != null) {
			eManager.persist(article);
			return true;
		}
		
		return false;	
	}
	
	public Article getArticle(int articleId) {
		return eManager.find(Article.class, articleId);
	}
	
	public Article getArticle(String title) {
		
		String qString ="SELECT a FROM Article a WHERE a.title LIKE :title";
		
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
		
		query.setParameter("title", title);
		
		Article article = query.getSingleResult();
		
		if (article != null) {
			return article;
			
		}else {
			logger.info("Error retriving article from base", article);
			return null;
		}
	}
	
	public List<Article> getArticleByDate(LocalDate localDate) {
		String q = "SELECT a FROM Article a WHERE a.localDate LIKE :localDate";
		
		TypedQuery<Article> query = eManager.createQuery(q, Article.class);
		
		query.setParameter("localDate", localDate);
		
		List<Article>  articles = query.getResultList();
		
		if (articles != null) {
			return articles;
			
		}else {
			logger.info("Error retriving article from base", articles);
			return null;
		}
	}
	
	public List<Article> getAllArticles(){
		
		String qString = "SELECT a FROM Article a";
				
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
		
		List<Article> articles =query.getResultList();
		
		return articles;
	}
	
	public Article editArticle(int articleId, Article article) {
		
		Article article1 = getArticle(articleId);
		
		if (article1 != null) {
			article1.setAuthor(article.getAuthor());
			article1.setTitle(article.getTitle());
			article1.setShortDescription(article.getShortDescription());
			article1.setCategory(article.getCategory());
			article1.setLocalDate(article.getLocalDate());
			
			return eManager.merge(article1);
		}
		logger.info("Error, object that you wanted to edit does not exist in the databse");
		return null;
		
	}
	
	
	public String deleteArticle(int articleId) {
		
		Article article = getArticle(articleId);
		
		if (article != null) {
			eManager.remove(article);
			return "Article with " + articleId + " has been removed from the database."; 
		}
		
		return "Error, article that you wanted to delete does not exist!";
	}
	
	public List<Article> searchByTitle(String title) {
		
		String qString = "SELECT a FROM Article a WHERE a.title LIKE CONCAT('%',:title,'%')";
		
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
		
		query.setParameter("title", title);
		
		List<Article> articles = query.getResultList();
		
		return articles;
	}
	
	public List<Article> searchByAuthor(String author) {
		
		String qString = "SELECT a FROM Article a WHERE a.author LIKE CONCAT('%',:author,'%')";
		
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
			
		query.setParameter("author", author);
		
		List<Article> articles = query.getResultList();
		
		return articles;
		
	}
	
	public List<Article> searchByShortDescription(String shortDescription) {
		
		String qString = "SELECT a FROM Article a WHERE a.shortDescription LIKE CONCAT('%',:shortDescription,'%')";
		
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
		
		query.setParameter("shortDescription", shortDescription);
			
		List<Article> articles = query.getResultList();
		
		return articles;
	}
	
		public List<Article> searchByDate(String localDate) {
		
		String qString = "SELECT a FROM Article a WHERE a.localDate LIKE CONCAT('%',:localDate,'%')";
		
		TypedQuery<Article> query = eManager.createQuery(qString, Article.class);
		
		query.setParameter("localDate", localDate);
			
		List<Article> articles = query.getResultList();
		
		return articles;
	}
	
	
}
