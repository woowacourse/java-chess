package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.List;

public abstract class Piece {

    private final TeamType teamType;
    private final String name;
    private final List<Direction> directions;

    public Piece(TeamType teamType, String name, List<Direction> directions) {
        this.teamType = teamType;
        this.name = name;
        this.directions = directions;
    }

    public abstract boolean isMovableTo(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate);

    public String getName() {
        if (teamType == TeamType.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public boolean isTeamOf(TeamType teamType) {
        return this.teamType == teamType;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
