package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class Rook extends InfinitePiece {

    private Rook(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Rook from(boolean isWhite) {
        return new Rook(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.UP),
                new Move(Direction.RIGHT)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
