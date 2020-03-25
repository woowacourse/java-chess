package domain.piece.position;

public class InvalidPositionException extends IllegalArgumentException {
	public static final String INVALID_POSITION = "잘못된 포지션을 입력했습니다.";

	public InvalidPositionException(String s) {
		super(s);
	}
}
