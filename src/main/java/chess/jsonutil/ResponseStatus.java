package chess.jsonutil;

public enum ResponseStatus {
	SUCCESS("success"),
	ERROR("error");

	private final String status;

	ResponseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
