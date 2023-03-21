package domain.squarestatus;

import domain.position.Position;
import domain.position.Route;
import domain.piece.Color;
import domain.type.Type;

public interface SquareStatus {

    Type getType();

    Color getColor();

    Route findRoute(final Position source, final Position target);

    boolean isSameColor(final Color color);

    boolean isDifferentColor(final Color color);

    boolean isSameType(final Type type);

    boolean isDifferentType (final Type type);

}
