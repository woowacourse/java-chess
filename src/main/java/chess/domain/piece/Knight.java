package chess.domain.piece;

import chess.domain.board.KnightMove;
import chess.domain.board.Square;
import java.util.List;

public class Knight extends Piece {
    private static final List<KnightMove> POSSIBLE_MOVES = List.of(KnightMove.values());

    public Knight(final Team team) {
        super(team, Role.KNIGHT);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final KnightMove knightMove) {
        return POSSIBLE_MOVES.contains(knightMove);
    }
}
