package chess.exception;

public class OtherPieceInPathException extends IllegalArgumentException {

    public OtherPieceInPathException(String message) {
        super(message);
    }
}
