package chess.controller;

public class ResultDto {
    private final ScoreDto scoreDto;
    private final WinnerDto winnerDto;

    public ResultDto(final ScoreDto scoreDto, final WinnerDto winnerDto) {
        this.scoreDto = scoreDto;
        this.winnerDto = winnerDto;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }

    public String getWinner() {
        return winnerDto.getWinner();
    }
}
