package jp.blogspot.horiga3.example.aop;

public class Output<T> {
	
	private String trxId;
	private int statusCode;
	private String statusMessage;
	private T data;

	public Output( String trxId, int statusCode, String statusMessage) {
		this.trxId = trxId;
		this.statusCode = statusCode;
		this.statusMessage = "";
	}
	
	public Output( String trxId, T data) {
		this.trxId = trxId;
		this.statusCode = 200;
		this.data = data;
		this.statusMessage = "";
	}
	
	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public String toString() {
		return new StringBuilder().append("@").append(this.trxId).append("-[")
				.append(this.statusCode).append(": ")
				.append(this.statusMessage).append("], ").append(data)
				.toString();
	}
}
