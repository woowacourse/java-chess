package chess.model.piece;

import static chess.model.PieceColor.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Rook extends Piece {

    private static final String EMBLEM = "R";
    private static final double SCORE = 5;

    private static final Piece ROOK_WHITE = new Rook(WHITE);
    private static final Piece ROOK_BLACK = new Rook(BLACK);

    private Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece colorOf(PieceColor pieceColor) {
        if (pieceColor.isWhite()) {
            return ROOK_WHITE;
        }
        return ROOK_BLACK;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isCross();
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
