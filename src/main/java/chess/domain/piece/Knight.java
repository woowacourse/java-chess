package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Knight extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.KNIGHT_UP_RIGHT, Move.KNIGHT_RIGHT_UP,
            Move.KNIGHT_RIGHT_DOWN, Move.KNIGHT_DOWN_RIGHT, Move.KNIGHT_DOWN_LEFT, Move.KNIGHT_LEFT_DOWN,
            Move.KNIGHT_LEFT_UP, Move.KNIGHT_UP_LEFT);

    public Knight(final Team team) {
        super(team, Role.KNIGHT);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
