package chess.domain.exception;

public class FileNotFoundException extends IllegalArgumentException {

    public FileNotFoundException() {
        super("존재하지 않는 File입니다.");
    }
}
