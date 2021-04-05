package chess.dto;

public class StatusCode {
    public static final int OK = 200;
    public static final int CREATE = 201;
    public static final int ACCEPTED = 202;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int INTERNAL_SERVER_ERROR = 500;

    private StatusCode() {}
}
