package chess.view.response;

import com.google.gson.JsonElement;

public class StandardResponse {
	private ResponseStatus status;
	private String message;
	private JsonElement data;

	public StandardResponse(ResponseStatus status) {
		this.status = status;
	}

	public StandardResponse(ResponseStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public StandardResponse(ResponseStatus status, JsonElement data) {
		this.status = status;
		this.data = data;
	}
}
