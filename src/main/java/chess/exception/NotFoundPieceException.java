package chess.exception;

public class NotFoundPieceException extends RuntimeException {
	public NotFoundPieceException() {
		super("해당 위치에서 체스 말을 찾을 수 없습니다.");
	}
}
