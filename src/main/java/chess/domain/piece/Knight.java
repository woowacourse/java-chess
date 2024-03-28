package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(final Color color, final Square square) {
        super(color, PieceType.KNIGHT, square);
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
        return Set.of(Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT, Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
                Movement.LEFT_LEFT_UP, Movement.LEFT_LEFT_DOWN, Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP);
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
