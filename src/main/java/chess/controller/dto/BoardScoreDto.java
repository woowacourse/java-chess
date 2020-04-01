package chess.controller.dto;

public class BoardScoreDto {
    private final double boardScore;

    public BoardScoreDto(double boardScore) {
        this.boardScore = boardScore;
    }

    public double getBoardScore() {
        return boardScore;
    }
}
