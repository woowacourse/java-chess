package chess.exception;

public class RankCanNotFindException extends RuntimeException {
    private static final String ERROR_MESSAGE = "올바른 Rank 좌표를 입력해주세요.";

    public RankCanNotFindException() {
        super(ERROR_MESSAGE);
    }
}
