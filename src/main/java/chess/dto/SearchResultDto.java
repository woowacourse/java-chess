package chess.dto;

public class SearchResultDto {

    private final String winner;
    private final UserIdsDto userIdsDto;
    private final ScoreDto scoreDto;

    public SearchResultDto(final String state, final UserIdsDto userIdsDto,
        final ScoreDto scoreDto) {
        this.winner = state;
        this.userIdsDto = userIdsDto;
        this.scoreDto = scoreDto;
    }

    public String getWinner() {
        return winner;
    }

    public String getWhiteUserId() {
        return userIdsDto.getWhiteUserId();
    }

    public String getBlackUserId() {
        return userIdsDto.getBlackUserId();
    }

    public double getWhiteScore() {
        return scoreDto.getWhite();
    }

    public double getBlackScore() {
        return scoreDto.getBlack();
    }
}
