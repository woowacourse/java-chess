package chess.domain.piece;

import java.util.Set;

import chess.domain.move.Direction;
import chess.domain.move.Move;

public class Rook extends InfinitePiece {

    public Rook(boolean isWhite) {
        super(isWhite, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.UP),
                new Move(Direction.DOWN),
                new Move(Direction.LEFT),
                new Move(Direction.RIGHT)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
