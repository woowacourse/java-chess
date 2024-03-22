package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Rook extends SlidingPiece {

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
