package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop extends InfinitePiece {

    private Bishop(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Bishop from(boolean isWhite) {
        return new Bishop(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(List.of(Direction.UP, Direction.RIGHT)));
        return moves;
    }
}
