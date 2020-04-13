package db;

public class DataBaseConnectionException extends NullPointerException {
    public DataBaseConnectionException() {
        super("데이터 베이스 연결 오류");
    }
}
