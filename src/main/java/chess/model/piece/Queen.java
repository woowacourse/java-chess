package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.PieceColor;

public class Queen extends Piece {

    private static final String EMBLEM = "Q";
    private static final double SCORE = 9;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isAllDirectional();
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
