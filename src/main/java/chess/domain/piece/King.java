package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class King extends QuadrantPiece {

    public King(boolean isWhite) {
        super(isWhite, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.UP),
                new Move(Direction.RIGHT),
                new Move(Direction.UP, Direction.RIGHT)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
