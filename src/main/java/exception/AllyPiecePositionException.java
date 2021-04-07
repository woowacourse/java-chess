package exception;

public class AllyPiecePositionException extends PieceMoveException {

    private static final String MESSAGE = "[Error] 같은 팀 기물이 존재하는 위치로 이동할 수 없습니다.";

    public AllyPiecePositionException() {
        super(MESSAGE);
    }

}
