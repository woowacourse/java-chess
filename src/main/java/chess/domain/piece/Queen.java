package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen extends InfinitePiece {

    private Queen(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Queen from(boolean isWhite) {
        return new Queen(isWhite, copyMoves(setUpMoves()));
    }

    private static Set<Move> setUpMoves() {
        Set<Move> moves = new HashSet<>();
        moves.add(new Move(List.of(Direction.UP)));
        moves.add(new Move(List.of(Direction.RIGHT)));
        moves.add(new Move(List.of(Direction.UP, Direction.RIGHT)));
        return moves;
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
