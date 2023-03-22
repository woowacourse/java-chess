package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.RIGHT_UP, Move.RIGHT_DOWN, Move.LEFT_UP, Move.LEFT_DOWN);

    public Bishop(Camp camp) {
        super(camp, Role.BISHOP);
    }

    @Override
    public boolean isMovable(Square source, Square target, Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
