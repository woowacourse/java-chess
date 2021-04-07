package chess.exception;

import java.util.NoSuchElementException;

public class NoSuchPieceException extends NoSuchElementException {
    public NoSuchPieceException() {
        super("존재하지 않는 말입니다.");
    }
}
