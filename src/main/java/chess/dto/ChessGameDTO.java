package chess.dto;

public class ChessGameDTO {
    private PiecesDTO piecesDto;
    private TeamDTO blackTeam;
    private TeamDTO whiteTeam;
    private boolean isRunning;

    public ChessGameDTO(final PiecesDTO piecesDto, final TeamDTO blackTeam, final TeamDTO whiteTeam, final boolean isRunning) {
        this.piecesDto = piecesDto;
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.isRunning = isRunning;
    }
}
