package model.shift;

import model.position.Position;
import model.position.Route;

import java.util.Set;

public interface Shift {
    Set<Route> routes(Position position);
}
