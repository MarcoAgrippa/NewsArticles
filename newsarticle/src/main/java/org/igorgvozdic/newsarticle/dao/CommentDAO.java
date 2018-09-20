package org.igorgvozdic.newsarticle.dao;

import org.igorgvozdic.newsarticle.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CommentDAO {

    Object object;
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger = LoggerFactory.getLogger(Comment.class);


    public boolean saveComment(Comment comment) {
        if (comment != null) {
            entityManager.persist(comment);
            return true;
        }
        return false;
    }

    public Comment getComment(int id) {

        if (id > 0) {
            Comment comment = entityManager.find(Comment.class, id);
            return comment;
        }

        return null;
    }

    public List<Comment> getAllComments() {

        String q = "SELECT c FROM Comment c";

        TypedQuery<Comment> query = entityManager.createQuery(q, Comment.class);

        List<Comment> comments = query.getResultList();

        return comments;
    }

    public Comment editComment(int id, Comment newComment) {
        Comment comment = getComment(id);

        if (comment != null) {
            comment.setAuthor(newComment.getAuthor());
            comment.setTitle(newComment.getTitle());
            comment.setContent(newComment.getContent());

            return entityManager.merge(comment);
        }
        logger.info("Error, object that you wanted to edit does not exist in the databse");
        return null;
    }

    public String deleteComment(int id) {

        if (id > 0) {
            Comment comment = getComment(id);
            entityManager.remove(comment);
            return "Comment with " + id + " has been deleted!";
        }

        return "Error, comment that you wanted to delete does not exist!";
    }


}
