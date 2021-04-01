package chess.domain.DTO;

public class ScoreDTO {
    private String scoreMessage;

    private ScoreDTO(String scoreMessage) {
        this.scoreMessage = scoreMessage;
    }

    public static ScoreDTO of(String scoreMessage) {
        return new ScoreDTO(scoreMessage);
    }
}
