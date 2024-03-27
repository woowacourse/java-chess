package model.shift;

import model.direction.Route;
import model.position.Position;

import java.util.Set;

public sealed interface Shift permits MultiShift, NoneShift, SingleShift {
    Set<Route> routes(final Position position);
}
