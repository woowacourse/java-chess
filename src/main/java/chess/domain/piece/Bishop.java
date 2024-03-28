package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bishop extends MultiShift {

    public Bishop(final Color color, final Square square) {
        super(color, PieceType.BISHOP, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return Stream.of(candidateLeftUpSquares(entirePieces),
                        candidateLeftDownSquares(entirePieces),
                        candidateRightDownSquares(entirePieces),
                        candidateRightUpSquares(entirePieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.RIGHT_DOWN);
    }
}
