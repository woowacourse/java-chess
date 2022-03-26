package chess.piece;

import chess.MoveType;
import chess.PieceColor;
import chess.Position;

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
