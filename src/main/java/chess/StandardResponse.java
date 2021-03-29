package chess;

import com.google.gson.JsonElement;

public class StandardResponse {

    private final StatusResponse statusResponse;
    private final String message;
    private final JsonElement data;

    public StandardResponse(StatusResponse statusResponse, String message, JsonElement data) {
        this.statusResponse = statusResponse;
        this.message = message;
        this.data = data;
    }
}
