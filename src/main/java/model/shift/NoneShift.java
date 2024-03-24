package model.shift;

import model.position.Position;
import model.position.Route;

import java.util.Set;

public class NoneShift implements Shift {
    @Override
    public Set<Route> routes(Position position) {
        return Set.of();
    }
}
