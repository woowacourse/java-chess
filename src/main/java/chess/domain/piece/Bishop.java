package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractStraightMovePattern;
import chess.domain.piece.movepattern.BishopMovePattern;

public final class Bishop extends Piece {

    private static final double POINT = 3.0;

    private final AbstractStraightMovePattern pattern = new BishopMovePattern();

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return pattern.canMove(src, dest);
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        return pattern.findDirection(src, dest);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
