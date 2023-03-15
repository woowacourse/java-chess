package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends FinitePiece {

    private Knight(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Knight from(boolean isWhite) {
        return new Knight(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(List.of(Direction.RIGHT, Direction.RIGHT, Direction.UP)));
        moves.add(new Move(List.of(Direction.RIGHT, Direction.UP, Direction.UP)));
        return moves;
    }
}
