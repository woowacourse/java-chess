package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.PieceColor;
import chess.vo.Position;

public class Bishop extends Piece {

    private static final String EMBLEM = "B";
    private static final double SCORE = 3;

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        return source.isDiagonal(target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

}
