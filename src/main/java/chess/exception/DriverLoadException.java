package chess.exception;

public class DriverLoadException extends RuntimeException {
    public DriverLoadException() {
        super("드라이버 로드에 실패했습니다!");
    }
}
