package org.igorgvozdic.newsarticle.errorhandeling;


public class CustomException {

	private int statusCode;
	
	private String statusMessage;
	
	
	public CustomException(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	public CustomException() {
		
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
	
	public static final CustomException OK = new CustomException(20, "Operation success");
	
	public static final CustomException INVALID_TITLE = new CustomException(41, "Title that you have entered is not valid");
	public static final CustomException INVALID_AUTHOR = new CustomException(42, "Author that you have entered is not valid");
	public static final CustomException INVALID_SHORT_DESCRIPTION = new CustomException(43, "Description that you have entered is either shorter that 1 char or longer than 100 chars");
	public static final CustomException INVALID_CATEGORY= new CustomException(44, "Category that you have entered is not valid");
	public static final CustomException NO_ARTICLES_IN_DATABASE = new CustomException(45, "No articles in database to fetch");
	public static final CustomException ARTICLE_NOT_FOUND = new CustomException(46, "Article could not be found in the database");
	public static final CustomException ERROR_CREATING_ARTICLE_IN_WEB_SERVICE = new CustomException(47, "Error in web service while trying to add article");
	public static final CustomException ERROR_DELETING_ADRTICLE_IN_WEB_SERVICE = new CustomException(48, "Error in web service while trying to delete article");
	public static final CustomException ERROR_EDITING_ARTICLE_IN_WEB_SERVICE = new CustomException(52, "Error while trying to edit article");
	public static final CustomException NO_ARTICLE_WITH_SUCH_TITLE = new CustomException(49, "There is no article in the database with such title");
	public static final CustomException NO_ARTICLE_WITH_SUCH_AUTHOR = new CustomException(50, "There is no article in the database with such author");
	public static final CustomException NO_ARTICLE_WITH_SUCH_DESCRIPTION = new CustomException(51, "There is no article in the database with such description");
	public static final CustomException NO_ARTICLE_FOUND = new CustomException(51, "There is no article found in the database");
	
	
	
	
}
