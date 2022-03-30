package chess.model.piece;

import static chess.model.PieceColor.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Bishop extends Piece {

    private static final String EMBLEM = "B";
    private static final double SCORE = 3;

    private static final Piece BISHOP_WHITE = new Bishop(WHITE);
    private static final Piece BISHOP_BLACK = new Bishop(BLACK);

    private Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece colorOf(PieceColor pieceColor) {
        if (pieceColor.isWhite()) {
            return BISHOP_WHITE;
        }
        return BISHOP_BLACK;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isDiagonal();
    }

    @Override
    String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
