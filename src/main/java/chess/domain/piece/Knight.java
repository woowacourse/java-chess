package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractOnceMovePattern;
import chess.domain.piece.movepattern.KnightMovePattern;

public final class Knight extends Piece {

    private static final double POINT = 2.5;

    private final AbstractOnceMovePattern pattern = new KnightMovePattern();

    public Knight(Color color) {
        super(color);
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
