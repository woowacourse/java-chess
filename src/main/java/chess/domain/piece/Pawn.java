package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractPawnMovePattern;

public final class Pawn extends Piece {

    private static final double POINT = 1.0;

    private final AbstractPawnMovePattern pattern;

    public Pawn(Color color) {
        super(color);
        this.pattern = AbstractPawnMovePattern.of(color);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        return pattern.canMove(source, destination);
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        return pattern.findDirection(source, destination);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
