package model;

/**
 * Classe Model Webhook contendo os atributos de um objeto Webhook.
 * 
 * @author Gabriel Queiroz
 * 
 */
public class Webhook {
	private String level;
	private String responseBody;
	private String requestTo;
	private String responseHeaders;
	private String responseStatus;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public Webhook(){
		
	}
	
	public Webhook(String level, String responseBody, String requestTo,
			String responseHeaders, String responseStatus) {
		super();
		this.level = level;
		this.responseBody = responseBody;
		this.requestTo = requestTo;
		this.responseHeaders = responseHeaders;
		this.responseStatus = responseStatus;
	}

	@Override
	public String toString() {
		return "Webhook [level=" + level + ", responseBody=" + responseBody
				+ ", requestTo=" + requestTo + ", responseHeaders="
				+ responseHeaders + ", responseStatus=" + responseStatus + "]";
	}
	
}
