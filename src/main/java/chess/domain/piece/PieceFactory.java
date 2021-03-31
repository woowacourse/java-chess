package chess.domain.piece;

public class PieceFactory {
    private PieceFactory() {
    }

    public static Piece from(char pieceName, Color color, char x, char y) {
        if (pieceName == Bishop.NAME_WHEN_BLACK || pieceName == Bishop.NAME_WHEN_WHITE) {
            return new Bishop(color, x, y);
        }
        if (pieceName == King.NAME_WHEN_BLACK || pieceName == King.NAME_WHEN_WHITE) {
            return new King(color, x, y);
        }
        if (pieceName == Knight.NAME_WHEN_BLACK || pieceName == Knight.NAME_WHEN_WHITE) {
            return new Knight(color, x, y);
        }
        if (pieceName == Pawn.NAME_WHEN_BLACK || pieceName == Pawn.NAME_WHEN_WHITE) {
            return new Pawn(color, x, y);
        }
        if (pieceName == Queen.NAME_WHEN_BLACK || pieceName == Queen.NAME_WHEN_WHITE) {
            return new Queen(color, x, y);
        }
        if (pieceName == Rook.NAME_WHEN_BLACK || pieceName == Rook.NAME_WHEN_WHITE) {
            return new Rook(color, x, y);
        }
        if (pieceName == Empty.NAME) {
            return new Empty(x, y);
        }
        throw new IllegalArgumentException("입력 값에 해당하는 말이 없습니다.");
    }
}
