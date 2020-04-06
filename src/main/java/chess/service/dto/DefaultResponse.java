package chess.service.dto;

import org.eclipse.jetty.http.HttpStatus;

public class DefaultResponse<T> {
    private int statusCode;
    private T data;
    private String message;

    public DefaultResponse(HttpStatus.Code code, T data, String message) {
        this.statusCode = code.getCode();
        this.data = data;
        this.message = message;
    }

    private DefaultResponse(HttpStatus.Code code, T data) {
        this(code, data, "");
    }

    public static <T> DefaultResponse<T> OK(T data, String message) {
        return new DefaultResponse<>(HttpStatus.Code.OK, data, message);
    }

    public static <T> DefaultResponse<T> OK(T data) {
        return new DefaultResponse<>(HttpStatus.Code.OK, data, null);
    }

    public static <Void> DefaultResponse<Void> ACCEPT() {
        return new DefaultResponse<>(HttpStatus.Code.ACCEPTED, null, null);
    }

    public static <T> DefaultResponse<T> CREATED(T data, String message) {
        return new DefaultResponse<>(HttpStatus.Code.CREATED, data, message);
    }

    public static <T> DefaultResponse<T> CREATED(T data) {
        return new DefaultResponse<>(HttpStatus.Code.CREATED, data, null);
    }

    public static <T> DefaultResponse<T> BADREQUEST(T data, String message) {
        return new DefaultResponse<>(HttpStatus.Code.BAD_REQUEST, data, message);
    }

    public static <T> DefaultResponse<T> BADREQUEST(T data) {
        return new DefaultResponse<>(HttpStatus.Code.BAD_REQUEST, data, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
