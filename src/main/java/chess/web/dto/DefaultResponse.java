package chess.web.dto;

import org.eclipse.jetty.http.HttpStatus;

public class DefaultResponse<T> {
    private HttpStatus.Code statusCode;
    private T data;
    private String message;

    public DefaultResponse(int code, T data, String message) {
        this.statusCode = HttpStatus.getCode(code);
        this.data = data;
        this.message = message;
    }

    private DefaultResponse(int code, T data) {
        this(code, data, "");
    }

    public static <T> DefaultResponse<T> OK(T data, String message) {
        return new DefaultResponse<>(HttpStatus.OK_200, data, message);
    }

    public static <T> DefaultResponse<T> CREATED(T data, String message) {
        return new DefaultResponse<>(HttpStatus.CREATED_201, data, message);
    }

    public static <T> DefaultResponse<T> CREATED(T data) {
        return new DefaultResponse<>(HttpStatus.CREATED_201, data, null);
    }

    public static <T> DefaultResponse<T> BADREQUEST(T data, String message) {
        return new DefaultResponse<>(HttpStatus.BAD_REQUEST_400, data, message);
    }

    public HttpStatus.Code getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
