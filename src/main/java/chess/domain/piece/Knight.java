package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends QuadrantPiece {

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

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
