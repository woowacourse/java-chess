package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.PieceColor;
import chess.vo.Position;

public class Rook extends Piece {

    private static final String EMBLEM = "R";
    private static final double SCORE = 5;

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        return source.isCross(target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
