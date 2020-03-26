package domain.piece.position;

public class InvalidPositionException extends IllegalArgumentException {
	public static final String INVALID_SOURCE_POSITION = "잘못된 말을 선택했습니다.";
	public static final String INVALID_TARGET_POSITION = "잘못된 목적지를 입력했습니다.";
	public static final String INVALID_BOUNDARY_POSITION = "체스판의 범위를 벗어났습니다.";

	public InvalidPositionException(String s) {
		super(s);
	}
}
