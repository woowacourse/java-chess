package chess.domain.piece;

import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;

public class Empty extends Piece {
    public Empty() {
        super(Team.EMPTY, PieceType.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        throw new PieceCanNotMoveException();
    }
}
