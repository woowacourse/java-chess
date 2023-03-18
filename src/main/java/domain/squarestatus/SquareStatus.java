package domain.squarestatus;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.type.Type;

public interface SquareStatus {

    Type getType();

    Color getColor();

    Route findRoute(final Position source, final Position target);

}
