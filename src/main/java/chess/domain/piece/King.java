package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class King extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.UP, Move.DOWN, Move.RIGHT, Move.LEFT, Move.RIGHT_UP,
            Move.RIGHT_DOWN, Move.LEFT_UP, Move.LEFT_DOWN);

    public King(final Team team) {
        super(team, Role.KING);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        if (POSSIBLE_MOVES.contains(move)) {
            return source.isMovableToTarget(target, move.getFile(), move.getRank());
        }
        return false;
    }
}
