package chess.dto;

import chess.domain.team.Team;

public class ChessGameDto {
    private PiecesDto piecesDto;
    private TeamDto blackTeam;
    private TeamDto whiteTeam;
    private boolean isRunning;

    public ChessGameDto(final PiecesDto piecesDto, final TeamDto blackTeam, final TeamDto whiteTeam, final boolean isRunning) {
        this.piecesDto = piecesDto;
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.isRunning = isRunning;
    }
}
