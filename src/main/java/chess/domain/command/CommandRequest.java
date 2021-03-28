package chess.domain.command;

import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;

public class CommandRequest {

    private final TeamType teamType;
    private final CommandTokens commandTokens;

    public CommandRequest(TeamType teamType, CommandTokens commandTokens) {
        this.teamType = teamType;
        this.commandTokens = commandTokens;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public Coordinate getCurrentCoordinate() {
        return Coordinate.from(commandTokens.findCurrentCoordinateToken());
    }

    public Coordinate getDestinationCoordinate() {
        return Coordinate.from(commandTokens.findDestinationCoordinateToken());
    }
}
