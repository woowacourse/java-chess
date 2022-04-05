package domain.piece.unit;

import domain.piece.property.Team;
import domain.position.Position;
import domain.piece.property.Direction;
import java.util.List;

public interface Piece {

    boolean availableMove(Position source, Position target, boolean targetIsNull);

    boolean isPawn();

    boolean checkOneAndTwoSouthNorthDirections(Position target);

    boolean checkSameTeam(final Team team);

    void calculateDirections(Position source);

    List<Position> calculateRoute(final Position source, final Position target);

    String getSymbolByTeam();

    String symbol();

    List<Direction> getDirections();

    Direction getDirection(Position target);
}
