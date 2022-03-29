package chess.model.piece;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Bishop extends Piece {

    private static final String EMBLEM = "B";
    private static final double SCORE = 3;

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isDiagonal();
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
