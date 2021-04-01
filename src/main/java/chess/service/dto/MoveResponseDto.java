package chess.service.dto;

public class MoveResponseDto {
    private String source;
    private String target;
    private ScoreDto scoreDto;

    public MoveResponseDto(final String source, final String target, final ScoreDto scoreDto) {
        this.source = source;
        this.target = target;
        this.scoreDto = scoreDto;
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
}
