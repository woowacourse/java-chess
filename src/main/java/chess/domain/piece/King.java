package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractOnceMovePattern;
import chess.domain.piece.movepattern.KingMovePattern;

public final class King extends Piece {

    private static final double POINT = 0.0;

    private final AbstractOnceMovePattern pattern = new KingMovePattern();

    public King(Color color) {
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
