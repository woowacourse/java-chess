package chess.exception;

public class FileCanNotFindException extends RuntimeException {
    private static final String ERROR_MESSAGE = "올바른 File 좌표를 입력해주세요.";

    public FileCanNotFindException() {
        super(ERROR_MESSAGE);
    }
}
