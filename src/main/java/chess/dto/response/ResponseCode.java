package chess.dto.response;

public enum ResponseCode {
    OK(200, "데이터를 조회하는 데 성공했습니다."),
    NO_CONTENT(204, "요청에 대한 처리가 성공했습니다. 그리고 응답할 컨텐츠는 존재하지 않습니다."),
    WRONG_ARGUMENTS(401, "잘못된 값을 입력했습니다."),
    SQL_ERROR(410, "SQL 관련 에러입니다.");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
