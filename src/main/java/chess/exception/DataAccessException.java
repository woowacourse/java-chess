package chess.exception;

public class DataAccessException extends RuntimeException{
    private static final String DATA_ACCESS_ERROR_MESSAGE = "데이터 접근시 에러가 발생하였습니다.";

    public DataAccessException(Throwable e) {
        super(DATA_ACCESS_ERROR_MESSAGE, e);
    }
}
