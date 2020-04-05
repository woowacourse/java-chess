package domain.piece.position;

public class InvalidPositionException extends IllegalArgumentException {
	public static final String INVALID_SOURCE_POSITION = "잘못된 말을 선택했습니다.";
	public static final String INVALID_BOUNDARY_POSITION = "체스판의 범위를 벗어났습니다.";
	public static final String HAS_PIECE_IN_ROUTE = "경로에 기물이 있습니다.";
	public static final String INVALID_STEP_SIZE = "말이 움직일 수 있는 칸 수가 아닙니다.";
	public static final String IS_IN_PLACE = "현재 위치에서 움직여야 합니다.";
	public static final String INVALID_DIRECTION = "현재 위치에서 목적지의 방향이 올바르지 않습니다.";
	public static final String HAS_OUR_TEAM_AT_TARGET_POSITION = "목적지에 아군이 있습니다.";
	public static final String HAS_PIECE_AT_TARGET_POSITION = "목적지에 기물이 있습니다.";
	public static final String INVALID_POSITION = "위치가 잘못되었습니다.";

	public InvalidPositionException(String s) {
		super(s);
	}
}
