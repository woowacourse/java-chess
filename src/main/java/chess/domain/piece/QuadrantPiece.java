package chess.domain.piece;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.move.Axis;
import chess.domain.move.Move;

public abstract class QuadrantPiece extends Piece {

    public QuadrantPiece(boolean isWhite, Set<Move> moves) {
        super(isWhite, copyMoves(moves));
    }

    private static Set<Move> copyMoves(Set<Move> moves) {
        return moves.stream()
                .flatMap(QuadrantPiece::flip)
                .collect(Collectors.toSet());
    }

    private static Stream<Move> flip(Move move) {
        return Stream.of(
                move,
                move.flipOver(Axis.HORIZON),
                move.flipOver(Axis.VERTICAL),
                move.flipOver(Axis.HORIZON).flipOver(Axis.VERTICAL)
        );
    }
}
