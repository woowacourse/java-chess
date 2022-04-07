package web.exception;

public class QueryException extends RuntimeException {

    public QueryException() {
        super("정보 업데이트 중 오류가 발생했습니다.");
    }
}
