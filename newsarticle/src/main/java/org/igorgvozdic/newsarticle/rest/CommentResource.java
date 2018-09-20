package org.igorgvozdic.newsarticle.rest;

import org.igorgvozdic.newsarticle.bussineslogic.CommentService;
import org.igorgvozdic.newsarticle.dto.CommentDTO;
import org.igorgvozdic.newsarticle.model.Comment;
import org.igorgvozdic.newsarticle.result.CommentResult;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CommentResource {

    @EJB
    private CommentService commentService;

    @GET
    public Comment getComments() {
        return new Comment();
    }

    @GET
    @Path("/{commentId}")
    public CommentResult getComment(@PathParam("id") int articleId, @PathParam("commentId") int id) {

        try {

        }
        return new CommentResult();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommentResult addComment(CommentDTO commentDTO) {
        return new CommentResult();
    }

    @PUT
    @Path("/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommentResult editComment(@PathParam("commentId") int id, CommentDTO commentDTO) {
        return new CommentResult();
    }

    @DELETE
    @Path("/{commentId}")
    public CommentResult deleteComment(@PathParam("commentId") int id) {
        return new CommentResult();
    }

}
