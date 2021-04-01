package chess.service.dto;

public class MoveResponseDto {
    private String source;
    private String target;
    private ScoreDto scoreDto;
    private boolean isGameOver;

    public MoveResponseDto(final String source, final String target, final ScoreDto scoreDto, final boolean isGameOver) {
        this.source = source;
        this.target = target;
        this.scoreDto = scoreDto;
        this.isGameOver = isGameOver;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public ScoreDto getScoreDto(){
        return scoreDto;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
