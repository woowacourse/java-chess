package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;

public abstract class Piece {

    private final TeamType teamType;
    private final String name;

    public Piece(TeamType teamType, String name) {
        this.teamType = teamType;
        this.name = name;
    }

    public abstract void move(Board board, Coordinate currentCoordinate, Coordinate coordinate);

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
}
