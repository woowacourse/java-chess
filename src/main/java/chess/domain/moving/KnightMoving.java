package chess.domain.moving;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.List;

public class KnightMoving extends SingleMoving {
    private final int[] rowDirection = {-1, 1, 2, 2, 1, -1, -2, -2};
    private final int[] colDirection = {2, 2, 1, -1, -2, -2, -1, 1};

    @Override
    public List<Position> allMovablePositions(final Piece piece, final Board board) {
        return super.movablePositions(piece, board, rowDirection, colDirection);
    }
}
