package chess.exception;

public class BlockedMovePathException extends InvalidMovementException {

    private static final String EXCEPTION_MESSAGE = "진로가 막혀있어 해당 위치로 이동할 수 없습니다.";

    public BlockedMovePathException() {
        super(EXCEPTION_MESSAGE);
    }
}
