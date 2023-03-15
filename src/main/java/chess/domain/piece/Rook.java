package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends InfinitePiece {

    private Rook(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Rook from(boolean isWhite) {
        return new Rook(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(List.of(Direction.UP)));
        moves.add(new Move(List.of(Direction.RIGHT)));
        return moves;
    }
}
