package chess.domain.piece;

import chess.constant.SquareType;
import chess.domain.board.position.Position;

public class EmptyPiece extends Piece {

    private static final String EMBLEM = ".";
    private static final double SCORE = 0;

    public EmptyPiece(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, SquareType squareType) {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
