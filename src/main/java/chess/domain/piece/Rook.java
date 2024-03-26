package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rook extends SlidingPiece {
    public Rook(final Color color, Square square) {
        super(color, PieceType.ROOK, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return Stream.of(candidateUpSquares(entirePieces),
                        candidateDownSquares(entirePieces),
                        candidateLeftSquares(entirePieces),
                        candidateRightSquares(entirePieces))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
