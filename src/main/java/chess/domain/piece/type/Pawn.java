package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Pawn extends Piece {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        if (this.position.isSecondRank()) {
           return this.position.isForwardDifference(target, 2) || this.position.isForwardDifference(target, 1);
        }
        return this.position.isForwardDifference(target, 1);
    }
}
