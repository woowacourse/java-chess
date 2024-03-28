package model.piece.role;

import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Square extends Role {
    public Square() {
        super(Color.UN_COLORED, ShiftPattern.NONE);
    }

    @Override
    protected Route findRouteByDirection(Direction direction, Position source) {
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
