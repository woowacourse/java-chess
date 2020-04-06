package chess.web.dto;

import chess.manager.ChessManager;

public class ChessBoardResponse {
    private Long id;
    private TeamScoreDto teamScoreDto;
    private TilesDto tilesDto;
    private String turn;

    public ChessBoardResponse(Long id, ChessManager chessManager) {
        this.id = id;
        this.teamScoreDto = new TeamScoreDto(chessManager);
        this.tilesDto = new TilesDto(chessManager);
        this.turn = chessManager.getCurrentTeam().name();
    }

    public Long getId() {
        return id;
    }

    public TeamScoreDto getTeamScoreDto() {
        return teamScoreDto;
    }

    public TilesDto getTilesDto() {
        return tilesDto;
    }

    public String getTurn() {
        return turn;
    }
}
