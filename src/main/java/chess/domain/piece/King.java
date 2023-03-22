package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class King extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.values());

    public King(Camp camp) {
        super(camp, Role.KING);
    }

    @Override
    public boolean isMovable(Square source, Square target, Move move) {
        if (POSSIBLE_MOVES.contains(move)) {
            return source.isMovableToTarget(target, move.getFile(), move.getRank());
        }
        return false;
    }
}
