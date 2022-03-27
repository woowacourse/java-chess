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
