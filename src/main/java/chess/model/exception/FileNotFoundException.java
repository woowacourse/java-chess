package chess.model.exception;

public class FileNotFoundException extends ChessException {

    public FileNotFoundException() {
        super(ChessExceptionType.FILE_NOT_FOUND);
    }
}
