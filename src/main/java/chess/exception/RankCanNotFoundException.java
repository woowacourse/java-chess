package chess.exception;

public class RankCanNotFoundException extends CustomException {
    public RankCanNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
