package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.Set;

public class Bishop extends InfinitePiece {

    private Bishop(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Bishop from(boolean isWhite) {
        return new Bishop(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        return Set.of(new Move(Direction.UP, Direction.RIGHT));
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
