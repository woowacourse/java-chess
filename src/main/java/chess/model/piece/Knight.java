package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.PieceColor;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5;
    private static final int BIG_MOVE = 2;
    private static final int SMALL_MOVE = 1;

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.rankDistance() == BIG_MOVE && path.fileDistance() == SMALL_MOVE ||
            path.rankDistance() == SMALL_MOVE && path.fileDistance() == BIG_MOVE;
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
