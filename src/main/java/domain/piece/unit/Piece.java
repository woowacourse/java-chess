package domain.piece.unit;

import domain.piece.property.Team;
import domain.position.Position;
import domain.utils.Direction;
import java.util.List;

public interface Piece {

    boolean checkSameTeam(final Team Team);

    boolean availableMove(final Position source, final Position target);

    void addDirectionalPosition(final Direction direction, final List<Position> positions);

    List<Position> calculateRoute(final Position target);

    String getSymbolByTeam();

    String symbol();
}
