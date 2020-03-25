package domain.piece.position;

public class InvalidPositionException extends IllegalArgumentException {
	public static final String INVALID_COLUMN = "column을 잘못입력했습니다.";

	public InvalidPositionException(String s) {
		super(s);
	}
}
