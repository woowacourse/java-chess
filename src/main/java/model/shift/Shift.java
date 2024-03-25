package model.shift;

import model.direction.Route;
import model.position.Position;

import java.util.Set;

public interface Shift {
    Set<Route> routes(final Position position);
}
