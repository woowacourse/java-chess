package chess.model.piece;

import static chess.model.PieceColor.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Queen extends Piece {

    private static final String EMBLEM = "Q";
    private static final double SCORE = 9;

    private static final Piece QUEEN_WHITE = new Queen(WHITE);
    private static final Piece QUEEN_BLACK = new Queen(BLACK);

    private Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece colorOf(PieceColor pieceColor) {
        if (pieceColor.isWhite()) {
            return QUEEN_WHITE;
        }
        return QUEEN_BLACK;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isAllDirectional();
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
