package chess.domain.dto;

import chess.domain.dto.response.ResponseDto;

public final class BoardDto implements ResponseDto {

    private final String team;
    private final boolean isGameOver;

    public BoardDto(final String team, final boolean isGameOver) {
        this.team = team;
        this.isGameOver = isGameOver;
    }

    public String team() {
        return team;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
