package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.PieceColor;
import chess.vo.Position;

public class EmptyPiece extends Piece {

    private static final String EMBLEM = ".";
    private static final double SCORE = 0;

    public EmptyPiece(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
