package chess.domain.dto;

import chess.domain.dto.response.ResponseDto;
import chess.domain.piece.Team;

public class BoardDto implements ResponseDto {

    private final long boardId;
    private final String team;
    private final boolean isGameOver;

    public BoardDto(final Team team, final boolean isGameOver) {
        this(0, team.teamName(), isGameOver);
    }

    public BoardDto(final long boardId, final String team, final boolean isGameOver) {
        this.boardId = boardId;
        this.team = team;
        this.isGameOver = isGameOver;
    }
}
