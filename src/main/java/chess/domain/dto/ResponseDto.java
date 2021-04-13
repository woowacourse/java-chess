package chess.domain.dto;

import com.google.gson.Gson;

public class ResponseDto<T> {

    private static final Gson gson = new Gson();

    private final ResponseStatus status;
    private final T payload;
    private final String message;

    public ResponseDto(ResponseStatus status, T payload, String message) {
        this.status = status;
        this.payload = payload;
        this.message = message;
    }

    public static <T> String ok(T payload) {
        return gson.toJson(new ResponseDto<>(ResponseStatus.OK, payload, null));
    }

    public static <T> String error(String message) {
        return gson.toJson(new ResponseDto<>(ResponseStatus.ERROR, null, message));
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public T getPayload() {
        return payload;
    }

    public String getMessage() {
        return message;
    }
}
