package chess.exception;

public class SQLConnectionException extends RuntimeException {
	private static final String message = "데이터베이스 연결에 실패했습니다.";

	public SQLConnectionException() {
		super(message);
	}
}
