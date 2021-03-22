package chess.exception;

public class GameIsNotStartException extends RuntimeException {

    public GameIsNotStartException() {
        super("[ERROR] 게임이 아직 시작 되지 않았습니다.");
    }
}
