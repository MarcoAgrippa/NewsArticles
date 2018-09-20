package org.igorgvozdic.newsarticle.rest;

import org.igorgvozdic.newsarticle.bussineslogic.ArticleService;
import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.ArticleException;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;
import org.igorgvozdic.newsarticle.model.Article;
import org.igorgvozdic.newsarticle.result.ArticleResult;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("articles")
public class ArticleResource {

    @EJB
    private ArticleService articleService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult getAllArticles() {

        try {
            List<ArticleDTO> articleDTOs = articleService.getAllArticles();

            return new ArticleResult(CustomException.OK, articleDTOs);
        } catch (ArithmeticException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);

        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult getArticleById(@PathParam("id") int id) {
        try {
            Article article = articleService.getArticle(id);
            ArticleDTO articleDTO = article.asFullArticleDTO();

            return new ArticleResult(CustomException.OK, articleDTO);
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ARTICLE_NOT_FOUND);
        }
    }

    @GET
    @Path("query")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult queryArticles(@QueryParam("title") String title,
                                       @QueryParam("author") String author,
                                       @QueryParam("shortdescription") String shortDescription,
                                       @QueryParam("date") String date,
                                       @QueryParam("all") String queryAll) {
        if (title != null && title.length() > 0) {
            return getArticlesByTitle(title);
        }

        if (author != null && author.length() > 0) {
            return getArticlesByAuthor(author);
        }

        if (shortDescription != null && shortDescription.length() > 0) {
            return getArticlesByShortDescription(shortDescription);
        }

        if (date != null && date.length() > 0) {
            return getArticleByDate(date);
        }

        if (queryAll != null && queryAll.length() > 0) {
            return searchAll(queryAll);
        }

        return new ArticleResult(CustomException.NO_ARTICLE_FOUND);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult addArticle(ArticleDTO articleDTO) {

        Article article = articleDTO.asArticle();

        try {
            articleService.addArticle(article);
            return new ArticleResult(CustomException.OK);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_CREATING_ARTICLE_IN_WEB_SERVICE);
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult editArticle(@PathParam("id") int id, ArticleDTO articleDTO) {

        try {
            Article article = articleDTO.asArticle();
            Article editedArticle = articleService.editArticle(id, article);

            return new ArticleResult(CustomException.OK, editedArticle.asFullArticleDTO());
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_EDITING_ARTICLE_IN_WEB_SERVICE);
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleResult deleteArticle(@PathParam("id") int id) {
        try {
            articleService.deleteArticle(id);

            return new ArticleResult(CustomException.OK);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.ERROR_DELETING_ADRTICLE_IN_WEB_SERVICE);
        }
    }

    @GET
    @Path("/{id}/comments")
    public CommentResource getComments() {
        return new CommentResource();
    }


    public ArticleResult getArticlesByTitle(String title) {

        try {
            List<ArticleDTO> articleDTOs = articleService.searchByTitle(title);

            return new ArticleResult(CustomException.OK, articleDTOs);
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
        }
    }


    public ArticleResult getArticlesByAuthor(String author) {

        try {
            List<ArticleDTO> articleDTOs = articleService.searchByAuthor(author);

            return new ArticleResult(CustomException.OK, articleDTOs);
        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
        }
    }

    public ArticleResult getArticlesByShortDescription(String shortDescription) {

        try {
            List<ArticleDTO> articleDTOs = articleService.searchByShortDescription(shortDescription);
            return new ArticleResult(CustomException.OK, articleDTOs);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
        }

    }

    public ArticleResult getArticleByDate(String date) {
        try {
            List<ArticleDTO> articleDTOs = articleService.searchArticleByDate(date);
            return new ArticleResult(CustomException.OK, articleDTOs);

        } catch (ArticleException e) {
            throw new ArticleException(CustomException.NO_ARTICLE_FOUND);
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
