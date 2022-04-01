package chess.domain.position.direction;

import chess.domain.position.Position;

public class HorizontalDirection implements Direction{
    @Override
    public boolean isOnDirection(Position from, Position to) {
        return isOnHorizontal(from, to);
    }

    public boolean isOnHorizontal(Position from, Position to) {
        return from.isSameYAxis(to);
    }
}
