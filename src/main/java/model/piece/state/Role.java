package model.piece.state;

import model.piece.Color;
import model.position.Position;
import model.position.Route;
import model.shift.Shift;

import java.util.Set;

public abstract class Role {

    private Color color;
    private Shift shift;

    protected Role(Color color, Shift shift) {
        this.color = color;
        this.shift = shift;
    }

    public void checkSameCamp(Role role) {
        if (this.color == role.color) {
            throw new IllegalArgumentException("같은 진영의 기물이 목적 지점에 위치합니다.");
        }
    }

    public boolean isOccupied() {
        return true;
    }

    public Route findRoute(Position source, Position destination) {
        return findPossibleAllRoute(source)
                .stream()
                .filter(route -> route.contains(destination))
                .map(route -> route.subRoute(destination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    public Set<Route> findPossibleAllRoute(Position position) {
        return shift.routes(position);
    }

    public Color getColor() {
        return color;
    }
}
