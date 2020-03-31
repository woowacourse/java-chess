package chess.score;

public class NegativeScoreCreateException extends IllegalArgumentException {
    private static final String NEGATIVE_SCORE_CREATE_ERROR_MESSAGE = "음수로 Score를 생성할 수 없습니다.";

    public NegativeScoreCreateException() {
        super(NEGATIVE_SCORE_CREATE_ERROR_MESSAGE);
    }
}
