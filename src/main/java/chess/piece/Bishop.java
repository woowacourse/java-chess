package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Bishop extends Piece {

    private static final String EMBLEM = "B";
    private static final double SCORE = 3f;


    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isDiagonal(target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

}
