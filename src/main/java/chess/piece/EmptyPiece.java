package chess.piece;

import chess.PieceColor;
import chess.Position;

public class EmptyPiece extends Piece {

    private static final String emblem = ".";
    private static final double SCORE = 0;

    public EmptyPiece(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
