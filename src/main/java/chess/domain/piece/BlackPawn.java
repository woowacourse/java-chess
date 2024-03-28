package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;

public class BlackPawn extends Pawn {

    public BlackPawn(Square square) {
        super(Color.BLACK, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findPawnLegalMoves(entirePieces, Color.BLACK);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.DOWN);
    }

    @Override
    public Set<Movement> capableOfAttackMovements() {
        return Set.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }
}
