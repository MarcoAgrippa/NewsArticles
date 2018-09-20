package org.igorgvozdic.newsarticle.result;

import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ArticleResult {

    private int resultCode;

    private String resultMessage;

    private ArticleDTO articleDTO;

    private List<ArticleDTO> articleDTOs;

    public ArticleResult() {

    }

    public ArticleResult(String resultMessage, ArticleDTO articleDTO) {
        this.resultMessage = resultMessage;
        this.articleDTO = articleDTO;
    }

    public ArticleResult(String resultMessage) {
        this.resultCode = resultCode;
    }

    public ArticleResult(CustomException customException) {
        this.resultCode = customException.getStatusCode();
        this.resultMessage = customException.getStatusMessage();
    }

    public ArticleResult(CustomException customException, ArticleDTO articleDTO) {
        this.resultCode = customException.getStatusCode();
        this.resultMessage = customException.getStatusMessage();
        this.articleDTO = articleDTO;
    }

    public ArticleResult(CustomException customException, List<ArticleDTO> articleDTOs) {
        this.resultCode = customException.getStatusCode();
        this.resultMessage = customException.getStatusMessage();
        this.articleDTOs = articleDTOs;

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

    public void setResultMessage(String resiltMessage) {
        this.resultMessage = resiltMessage;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    public List<ArticleDTO> getArticleDTOs() {
        return articleDTOs;
    }

    public void setArticleDTOs(List<ArticleDTO> articleDTOs) {
        this.articleDTOs = articleDTOs;
    }

}
