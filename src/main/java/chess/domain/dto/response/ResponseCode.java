package chess.domain.dto.response;

public enum ResponseCode {
    SUCCESS(200, "응답 성공"),
    GAME_OVER(212, "게임 종료"),
    RUN(204, "게임중"),
    ERROR(400, "");

    private final int code;
    private final String message;

    ResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
