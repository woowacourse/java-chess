package model.piece.state;

import java.util.List;
import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Square extends Role {
    public Square() {
        super(Color.UN_COLORED, List.of());
    }

    @Override
    protected Route findMovingPatternRoute(Direction direction, Position movedPosition) {
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
