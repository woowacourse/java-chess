package chess.piece;

import chess.MoveType;
import chess.PieceColor;
import chess.Position;

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
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        return source.isAllDirectional(target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
