package chess.utils;

public class KingIsDeadException extends RuntimeException {
    private static final String msg = "[Error] 왕이 잡혔기 때문에 게임이 끝났습니다.";

    public KingIsDeadException() {
        super(msg);
    }
}
