package chess.dto;

import chess.domain.TeamColor;

public class ChessGameDto {

    private final long gameId;
    private final TeamColor teamColor;

    public ChessGameDto(long gameId, TeamColor teamColor) {
        this.gameId = gameId;
        this.teamColor = teamColor;
    }

    public long getGameId() {
        return gameId;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

}
