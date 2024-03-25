package model.shift;

import model.position.Position;
import model.direction.Route;

import java.util.Set;

public class NoneShift implements Shift {
    @Override
    public Set<Route> routes(final Position position) {
        return Set.of();
    }
}
