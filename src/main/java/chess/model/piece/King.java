package chess.model.piece;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class King extends Piece {

    private static final String EMBLEM = "K";
    private static final double SCORE = 0;
    private static final int MOVE_DISTANCE = 1;

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isAllDirectional() && path.fileDistance() <= MOVE_DISTANCE && path.rankDistance() <= MOVE_DISTANCE;
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
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
