package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Queen extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Queen(final Camp camp, final Square position) {
        super(camp, position);
    }

    private static List<Move> makePossibleMove() {
        return List.of(
                Move.UP,
                Move.DOWN,
                Move.LEFT,
                Move.RIGHT,
                Move.UP_LEFT,
                Move.UP_RIGHT,
                Move.DOWN_LEFT,
                Move.DOWN_RIGHT
        );
    }

    @Override
    public boolean isMovable(final Square target, final Move move, final boolean isPathBlocked) {
        return possibleMoves.contains(move) && !isPathBlocked;
    }
}
