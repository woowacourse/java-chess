package chess.model.piece;

import static chess.model.PieceColor.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5;
    private static final int TWO_STEP = 2;
    private static final int ONE_STEP = 1;

    private static final Piece KNIGHT_WHITE = new Knight(WHITE);
    private static final Piece KNIGHT_BLACK = new Knight(BLACK);

    private Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece colorOf(PieceColor pieceColor) {
        if (pieceColor.isWhite()) {
            return KNIGHT_WHITE;
        }
        return KNIGHT_BLACK;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.rankDistance() == TWO_STEP && path.fileDistance() == ONE_STEP ||
            path.rankDistance() == ONE_STEP && path.fileDistance() == TWO_STEP;
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKnight() {
        return true;
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
