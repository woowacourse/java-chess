package dto;

import domain.Team;
import domain.player.Player;
import service.ChessGameStatus;

public record ChessGame(
        Player blackPlayer,
        Player whitePlayer,
        Team currentTeam,
        ChessGameStatus status
) {
}
