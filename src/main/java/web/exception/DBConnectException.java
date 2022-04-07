package web.exception;

public class DBConnectException extends RuntimeException {

    public DBConnectException() {
        super("서버 접속 중 에러가 발생했습니다");
    }
}
