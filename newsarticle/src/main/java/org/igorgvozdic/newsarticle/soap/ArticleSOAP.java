package org.igorgvozdic.newsarticle.soap;

import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.model.Category;
import org.igorgvozdic.newsarticle.result.ArticleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ArticleSOAP {

    @EJB
    private ArticleService articleService;

    private Logger logger = LoggerFactory.getLogger(ArticleSOAP.class);


    public ArticleResult addArticle(@WebParam(name = "title") String title,
                                    @WebParam(name = "author") String author,
                                    @WebParam(name = "shortDescription") String shortDescription,
                                    @WebParam(name = "category") Category category) {

        ArticleDTO articleDTO = new ArticleDTO(title, author, shortDescription, category);
        Article article = articleDTO.asArticle();

        try {
            articleService.addArticle(article);

            return new ArticleResult(CustomException.OK);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_CREATING_ARTICLE_IN_WEB_SERVICE);

        }
    }


    public ArticleResult getArticle(int id) {
        try {
            Article article = articleService.getArticle(id);
            ArticleDTO articleDTO = article.asFullArticleDTO();

            return new ArticleResult(CustomException.OK, articleDTO);
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ARTICLE_NOT_FOUND);
        }
    }

    public ArticleResult getArticlesByDate(String date) {
        try {
            List<ArticleDTO> articleDTOs = articleService.searchArticleByDate(date);

            return new ArticleResult(CustomException.OK, articleDTOs);
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
        }
    }

    public ArticleResult getAllArticles() {
        try {
            List<ArticleDTO> articles = articleService.getAllArticles();
            return new ArticleResult(CustomException.OK, articles);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLES_IN_DATABASE);
        }

    }

    public ArticleResult editArticle(@WebParam(name = "article_id") int id,
                                     @WebParam(name = "title") String title,
                                     @WebParam(name = "author") String author,
                                     @WebParam(name = "shortDescription") String shortDescription,
                                     @WebParam(name = "category") Category category) {

        try {
            ArticleDTO articleDTO = new ArticleDTO(title, author, shortDescription, category);
            Article article = articleDTO.asArticle();

            Article article2 = articleService.editArticle(id, article);

            ArticleDTO articleDTO2 = article2.asFullArticleDTO();
            return new ArticleResult(CustomException.OK, articleDTO2);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_EDITING_ARTICLE_IN_WEB_SERVICE);
        }

    }


    public ArticleResult deleteArticle(int id) {

        try {
            articleService.deleteArticle(id);
            return new ArticleResult(CustomException.OK);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_DELETING_ADRTICLE_IN_WEB_SERVICE);
        }
    }

    public ArticleResult findArticlesByTitle(String title) {
        try {
            List<ArticleDTO> articles = articleService.searchByTitle(title);
            return new ArticleResult(CustomException.OK, articles);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_TITLE);
        }
    }

    public ArticleResult findArticlesByAuthor(String author) {
        try {
            List<ArticleDTO> articles = articleService.searchByAuthor(author);
            return new ArticleResult(CustomException.OK, articles);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_TITLE);
        }
    }

    public ArticleResult findArticleByShortDescription(String shortDescription) {

        try {
            List<ArticleDTO> articleDTOs = articleService.searchByShortDescription(shortDescription);

            return new ArticleResult(CustomException.OK, articleDTOs);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_WITH_SUCH_DESCRIPTION);
        }
    }

    public ArticleResult searchAll(String queryString) {
        try {
            List<ArticleDTO> articleDTOs = articleService.searchAll(queryString);

            return new ArticleResult(CustomException.OK, articleDTOs);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
        }
    }

}
