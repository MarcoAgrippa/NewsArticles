package org.igorgvozdic.newsarticle.errorhandeling;

public class ArticleException extends RuntimeException{
	
	private int statusCode;
	
	private String statusMessage;
	
	public ArticleException(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	public ArticleException(String statusMessage) {
		super(statusMessage);
	}
	
	public ArticleException(CustomException customException) {
		this(customException.getStatusCode(), customException.getStatusMessage());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

}
