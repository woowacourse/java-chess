package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.route.Route;

public interface Placeable {
    Route findRoute(Position fromPosition, Position toPosition);

    boolean isTeam(Team team);

    String getAcronym();

    double getScore();

    Team getTeam();

    PieceType getPieceType();

    boolean isOppositeTeam(Team team);

    boolean isEmpty();

    boolean isNotEmpty();
}
