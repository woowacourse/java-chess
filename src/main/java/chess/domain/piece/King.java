package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends QuadrantPiece {

    private King(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static King from(boolean isWhite) {
        return new King(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(List.of(Direction.UP)));
        moves.add(new Move(List.of(Direction.RIGHT)));
        moves.add(new Move(List.of(Direction.UP, Direction.RIGHT)));
        return moves;
    }
}
