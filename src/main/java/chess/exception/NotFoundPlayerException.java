package chess.exception;

public class NotFoundPlayerException extends RuntimeException {
	public NotFoundPlayerException() {
		super("해당 플레이어가 존재하지 않습니다.");
	}
}
