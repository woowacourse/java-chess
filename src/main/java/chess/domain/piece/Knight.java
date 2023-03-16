package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class Knight extends QuadrantPiece {

    public Knight(boolean isWhite) {
        super(isWhite, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.RIGHT, Direction.RIGHT, Direction.UP),
                new Move(Direction.RIGHT, Direction.UP, Direction.UP)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
