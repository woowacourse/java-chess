package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Queen extends Piece {
    private static final List<Move> POSSIBLE_MOVES = List.of(Move.values());

    public Queen(Camp camp) {
        super(camp, Role.QUEEN);
    }
    
    @Override
    public boolean isMovable(Square source, Square target, Move move) {
        return POSSIBLE_MOVES.contains(move);
    }
}
