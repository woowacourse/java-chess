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
        if (isInitPosition()) {
           return this.position.isForwardWithDistance(target, 2) || this.position.isForwardWithDistance(target, 1);
        }
        return this.position.isForwardWithDistance(target, 1);
    }

    private boolean isInitPosition() {
        return this.position.isTwoRank(); //TODO 네이밍 고려
    }
}
