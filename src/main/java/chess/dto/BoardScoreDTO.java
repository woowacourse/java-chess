package chess.dto;

public class BoardScoreDTO {
    private final double boardScore;
    private final String team;

    public BoardScoreDTO(final double boardScore, final String team) {
        this.boardScore = boardScore;
        this.team = team;
    }

    public double getBoardScore() {
        return boardScore;
    }

    public String getTeam() {
        return this.team;
    }
}
