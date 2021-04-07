package exception;

public class DataBaseException extends RuntimeException {

    private static final String MESSAGE = "[Error] 데이터 베이스 오류 발생";

    public DataBaseException() {
        super(MESSAGE);
    }

}
