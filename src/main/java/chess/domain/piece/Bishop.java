package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bishop extends SlidingPiece {

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
}
