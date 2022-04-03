package chess.web.dto;

public class ApiResult {

    private final boolean success;
    private final Object response;
    private final Object error;

    private ApiResult(boolean success, Object response, Object error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }

    public static ApiResult succeed(Object response) {
        return new ApiResult(true, response, null);
    }

    public static ApiResult failed(Object error) {
        return new ApiResult(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResponse() {
        return response;
    }

    public Object getError() {
        return error;
    }
}
