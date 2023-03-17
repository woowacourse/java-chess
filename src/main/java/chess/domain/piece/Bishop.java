package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class Bishop extends InfinitePiece {

    public Bishop(Color color) {
        super(color, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(new Move(Direction.UP, Direction.RIGHT));
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
