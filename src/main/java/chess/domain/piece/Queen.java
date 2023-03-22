package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Queen extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.UP, Move.DOWN, Move.RIGHT, Move.LEFT, Move.RIGHT_UP,
            Move.RIGHT_DOWN, Move.LEFT_UP, Move.LEFT_DOWN);

    public Queen(final Team team) {
        super(team, Role.QUEEN);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
