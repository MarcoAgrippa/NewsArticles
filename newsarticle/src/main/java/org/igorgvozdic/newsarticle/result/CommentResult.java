package org.igorgvozdic.newsarticle.result;

import org.igorgvozdic.newsarticle.dto.CommentDTO;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class CommentResult {

    private int resultCode;

    private String resultMessage;

    private CommentDTO commentDTO;

    private List<CommentDTO> commentDTOs;

    public CommentResult() {

    }

    public CommentResult(CustomException customException, CommentDTO commentDTO) {
        this.resultCode = customException.getStatusCode();
        this.resultMessage = customException.getStatusMessage();
        this.commentDTO = commentDTO;
    }

    public CommentResult(CustomException customException, List<CommentDTO> commentDTOs) {
        this.resultCode = customException.getStatusCode();
        this.resultMessage = customException.getStatusMessage();
        this.commentDTOs = commentDTOs;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
