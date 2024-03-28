package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends Piece {

    public King(final Color color, Square square) {
        super(color, PieceType.KING, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        Set<Square> candidateSquares = findCandidateSquares();
        for (Piece other : entirePieces) {
            removeIfAllyExist(other, candidateSquares);
        }
        return candidateSquares;
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT,
                Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.RIGHT_DOWN);
    }

    public void removeIfAllyExist(Piece other, Set<Square> candidateSquares) {
        if (candidateSquares.contains(other.currentSquare()) && isAllyOf(other)) {
            candidateSquares.remove(other.currentSquare());
        }
    }

    public Set<Square> findCandidateSquares() {
        Square currentSquare = currentSquare();
        return movements().stream()
                .filter(currentSquare::canMove)
                .map(currentSquare::move)
                .collect(Collectors.toSet());
    }
}
