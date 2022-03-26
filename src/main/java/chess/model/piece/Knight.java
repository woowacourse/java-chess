package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.PieceColor;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5;

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.rankDistance() == 2 && path.fileDistance() == 1 ||
            path.rankDistance() == 1 && path.fileDistance() == 2;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
