package org.igorgvozdic.newsarticle.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;

@XmlRootElement
public class Result {

	private int resultCode;
	
	private String resultMessage;
	
	private ArticleDTO articleDTO;
	
	private List<ArticleDTO> articleDTOs;
	
	private CustomException customException;
	
	public Result() {
		
	}
	
	public Result(String resultMessage, ArticleDTO articleDTO) {
		this.resultMessage = resultMessage;
		this.articleDTO = articleDTO;
	}
	
	public Result(String resultMessage) {
		this.resultCode = resultCode;
	}
	
	public Result(CustomException customException) {
		this.resultCode = customException.getStatusCode();
		this.resultMessage = customException.getStatusMessage();
	}
	
	public Result (CustomException customException, ArticleDTO articleDTO) {
		this.resultCode = customException.getStatusCode();
		this.resultMessage = customException.getStatusMessage();
		this.articleDTO = articleDTO;
	}
	
	public Result (CustomException customException, List<ArticleDTO> articleDTOs) {
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
