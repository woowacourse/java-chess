package model.piece.state;

import java.util.Set;
import model.Position;

public final class Square implements Role {
    @Override
    public Set<Position> possiblePositions(Position position) {
        return Set.of();
    }
}
