package org.igorgvozdic.newsarticle.bussineslogic;

import org.igorgvozdic.newsarticle.dao.ArticleDAO;
import org.igorgvozdic.newsarticle.dao.CommentDAO;
import org.igorgvozdic.newsarticle.model.Comment;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CommentService {

    @EJB
    private CommentDAO commentDAO;

    @EJB
    private ArticleDAO articleDAO;

    public void addComment(int articleId, Comment comment) {

    }

    public List<Comment> getAllCommentsForArticleId(int articleId) {

        List<Comment> comments = articleDAO.getArticle(articleId).getComments();

        return comments;
    }

    public Comment getCommentForArticleId(int articleId, int commentId) {

        List<Comment> comments = articleDAO.getArticle(articleId).getComments();

        return comments.get(commentId);
    }

    public Comment editComment(int articleId, int commentId, Comment newComment) {

        List<Comment> comments = articleDAO.getArticle(articleId).getComments();
        Comment comment = comments.get(commentId);

        Comment editedComment = commentDAO.editComment(comment, newComment);
    }

    public void deleteComment(int articleId, int commentId) {

        List<Comment> comments = articleDAO.getArticle(articleId).getComments();

    }
}
