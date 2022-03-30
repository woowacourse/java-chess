package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractStraightMovePattern;
import chess.domain.piece.movepattern.QueenMovePattern;

public final class Queen extends Piece {

    private static final double POINT = 9.0;

    private final AbstractStraightMovePattern pattern = new QueenMovePattern();

    public Queen(Color color) {
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
