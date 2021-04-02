package dto.response;

import domain.board.Score;

public class ScoreResponseDto {

    private final double blackScore;
    private final double whiteScore;

    public ScoreResponseDto(Score blackScore, Score whiteScore) {
        this.blackScore = blackScore.getValue();
        this.whiteScore = whiteScore.getValue();
    }

}
