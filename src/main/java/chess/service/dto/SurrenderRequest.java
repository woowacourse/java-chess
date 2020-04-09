package chess.service.dto;

public class SurrenderRequest {
    private Long gameId;
    private String loseTeam;

    public SurrenderRequest(Long gameId, String loseTeam) {
        this.gameId = gameId;
        this.loseTeam = loseTeam;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getLoseTeam() {
        return loseTeam;
    }
}
