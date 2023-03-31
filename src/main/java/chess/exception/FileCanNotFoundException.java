package chess.exception;

public class FileCanNotFoundException extends CustomException {
    public FileCanNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
