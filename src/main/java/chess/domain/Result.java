package chess.domain;

import java.util.Arrays;

public enum Result {
    BLACK_WIN("흑색 진영의 승리"),
    BLACK_LOSE("백색 진영의 승리"),
    DRAW("무승부");

    private final String message;

    Result(final String message) {
        this.message = message;
    }

    public static Result from(final Result hasBlackWon) {
        return Arrays.stream(Result.values())
            .filter(it -> it == hasBlackWon)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("승패를 알 수 없는 상태입니다."));
    }

    public String getMessage() {
        return this.message;
    }
}
