package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public class WhitePawn extends Pawn {

    public WhitePawn(Square square) {
        super(Color.WHITE, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findWhitePawnLegalMoves(entirePieces);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP);
    }

    @Override
    public Set<Movement> capableOfAttackMovements() {
        return Set.of(Movement.LEFT_UP, Movement.RIGHT_UP);
    }

    private Set<Square> findWhitePawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>(capableOfAttack(existPieces));
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOfWhitePawn()) {
            addStartPawnMovableSquare(existPieces, currentSquare, squares);
            return squares;
        }
        addMovableSquare(existPieces, currentSquare, squares);
        return squares;
    }
}
