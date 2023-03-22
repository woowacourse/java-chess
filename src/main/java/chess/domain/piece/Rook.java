package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Rook extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.UP, Move.DOWN, Move.RIGHT, Move.LEFT);

    public Rook(Camp camp) {
        super(camp, Role.ROOK);
    }

    @Override
    public boolean isMovable(Square source, Square target, Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
