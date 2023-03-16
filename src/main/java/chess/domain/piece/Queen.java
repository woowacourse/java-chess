package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class Queen extends InfinitePiece {

    private Queen(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Queen from(boolean isWhite) {
        return new Queen(isWhite, copyMoves(setUpMoves()));
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
        return PieceType.QUEEN;
    }
}
