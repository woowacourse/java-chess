package chess.domain.board;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.board.movePattern.AbstractPawnMovePattern;

public final class Pawn extends Piece {

    private static final double POINT = 1.0;

    private final AbstractPawnMovePattern pattern;

    public Pawn(Color color) {
        super(color);
        this.pattern = AbstractPawnMovePattern.of(color);
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
