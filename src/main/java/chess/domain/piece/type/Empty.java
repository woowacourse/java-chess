package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Empty extends Piece {

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public boolean canMoveTo(final Position soutce, final Position target) {
        return false;
    }

    @Override
    public Set<Position> getRoute(final Position source, final Position target) {
        return new HashSet<>();
    }
}
