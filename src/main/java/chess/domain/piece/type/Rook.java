package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    public boolean canMoveTo(final Position target) {
        if (this.position.isStraightWith(target)) {
            return true;
        }
        return false;
    }
}
