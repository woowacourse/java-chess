package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;

public class Empty extends Piece {
    public Empty() {
        super(Team.EMPTY, Role.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Direction direction) {
        throw new PieceCanNotMoveException();
    }
}
