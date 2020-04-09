package chess.exception;

public class SQLAccessException extends RuntimeException {
	private static final String message = "데이터베이스 연결, 쿼리 관련 오류";

	public SQLAccessException() {
		super(message);
	}
}
