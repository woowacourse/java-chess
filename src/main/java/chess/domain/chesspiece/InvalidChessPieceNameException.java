package chess.domain.chesspiece;

public class InvalidChessPieceNameException extends IllegalArgumentException {
    public InvalidChessPieceNameException() {
    }

    public InvalidChessPieceNameException(String s) {
        super(s);
    }
}
