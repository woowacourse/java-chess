package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Rook extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.UP, Move.DOWN, Move.RIGHT, Move.LEFT);

    public Rook(final Team team) {
        super(team, Role.ROOK);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
