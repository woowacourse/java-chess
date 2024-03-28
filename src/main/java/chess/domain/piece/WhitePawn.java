package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Set;

public class WhitePawn extends Pawn {

    public WhitePawn(Square square) {
        super(Color.WHITE, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        return findPawnLegalMoves(entirePieces, Color.WHITE);
    }

    @Override
    protected Set<Movement> movements() {
        return Set.of(Movement.UP);
    }

    @Override
    public Set<Movement> capableOfAttackMovements() {
        return Set.of(Movement.LEFT_UP, Movement.RIGHT_UP);
    }
}
