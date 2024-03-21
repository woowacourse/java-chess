package model.piece.state;

import java.util.List;
import java.util.Set;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Square extends Role {
    public Square() {
        super(Color.UN_COLORED, List.of());
    }

    @Override
    public Set<Route> possibleRoutes(Position position) {
        return Set.of(new Route(List.of(position)));
    }

    @Override
    public boolean isOccupied(){
        return false;
    }
}
