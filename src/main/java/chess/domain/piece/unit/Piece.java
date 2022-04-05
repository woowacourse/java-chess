package chess.domain.piece.unit;

import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import chess.domain.piece.property.Direction;
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
