package domain.chessboard;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;

public interface SquareStatus {

    Type getType();

    Color getColor();

    Route findRoute(final Position source, final Position target);
}
