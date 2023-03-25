package chess.domain.service.dto;

import chess.domain.command.Turn;

public class UpdateTurnDto {

    private Long gameId;
    private String turn;

    public UpdateTurnDto(Long gameId, Turn turnToUpdate) {
        this.gameId = gameId;
        this.turn = turnToUpdate.getDisplayName();
    }

    public Long getGameId() {
        return gameId;
    }

    public String getTurn() {
        return turn;
    }
}
