package chess.domain.piece;

import chess.constant.MoveType;
import chess.domain.piece.constant.PieceColor;
import chess.domain.board.Position;

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
