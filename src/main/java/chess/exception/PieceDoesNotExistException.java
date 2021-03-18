package chess.exception;

public class PieceDoesNotExistException extends ChessException{
    public PieceDoesNotExistException(String message) {
        super(message);
    }
}
