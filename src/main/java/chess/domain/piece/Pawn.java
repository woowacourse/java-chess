package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

public class Pawn extends Piece {

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
}
