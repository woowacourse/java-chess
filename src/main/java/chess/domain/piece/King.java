package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Position;

public class King extends JumperPiece {

    public King(final Color color) {
        super(color);
    }

    @Override
    protected boolean canNotMovable(final Position from, final Position to, final Movement movement) {
        return !from.moveBy(movement).equals(to);
    }
}
