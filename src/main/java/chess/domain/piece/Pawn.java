package chess.domain.piece;

import chess.domain.piece.strategy.PawnStrategy;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, new PawnStrategy(PawnVector.getVectorsByColor(color)));
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
