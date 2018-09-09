package org.igorgvozdic.newsarticle.result;

import java.util.List;

import org.igorgvozdic.newsarticle.dto.ArticleDTO;
import org.igorgvozdic.newsarticle.errorhandeling.CustomException;

public class Result {

	private int resultCode;
	
	private String resiltMessage;
	
	private ArticleDTO articleDTO;
	
	private List<ArticleDTO> articleDTOs;
	
	private CustomException customException;
	
	public Result() {
		
	}
	
	public Result(String resultMessage, ArticleDTO articleDTO) {
		this.resiltMessage = resultMessage;
		this.articleDTO = articleDTO;
	}
	
	public Result(int resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resiltMessage = resultMessage;
	}
	
	public Result(String resultMessage) {
		this.resultCode = resultCode;
	}
	
	public Result(int resultCode, String resultMessage, ArticleDTO articleDTO) {
		this(resultCode, resultMessage);
		this.articleDTO = articleDTO;
	}
	
	public Result (CustomException customException, ArticleDTO articleDTO) {
		this.customException = customException;
		this.articleDTO = articleDTO;
	}
	
	public Result (CustomException customException, List<ArticleDTO> articleDTOs) {
		this.customException = customException;
		this.articleDTOs = articleDTOs;
	
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResiltMessage() {
		return resiltMessage;
	}

	public void setResiltMessage(String resiltMessage) {
		this.resiltMessage = resiltMessage;
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
