package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public class BlackPawn extends Pawn {

    public BlackPawn(Square square) {
        super(Color.BLACK, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findBlackPawnLegalMoves(entirePieces);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.DOWN);
    }

    @Override
    public Set<Movement> capableOfAttackMovements() {
        return Set.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }

    private Set<Square> findBlackPawnLegalMoves(Set<Piece> existPieces) {
        Set<Square> squares = new HashSet<>(capableOfAttack(existPieces));
        Square currentSquare = currentSquare();
        if (currentSquare.isStartRankOfBlackPawn()) {
            addStartPawnMovableSquare(existPieces, currentSquare, squares);
            return squares;
        }
        addMovableSquare(existPieces, currentSquare, squares);
        return squares;
    }
}
