package chess.model.piece;

public enum ChessPieceColor {
    WHITE, BLACK;

    public static ChessPieceColor nextTurnColor(final ChessPieceColor thisTurnColor) {
        return thisTurnColor == WHITE ? BLACK : WHITE;
    }
}
