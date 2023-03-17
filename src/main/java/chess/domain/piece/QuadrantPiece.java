package chess.domain.piece;

import chess.domain.move.Move;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                move.flipVertical(),
                move.flipHorizontal(),
                move.flipVertical().flipHorizontal()
        );
    }
}
