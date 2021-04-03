package chess.dto;

import chess.domain.game.state.GameState;

public final class GameStateDto {

    private String currentState;
    private BoardDto board;

    public GameStateDto(GameState gameState) {
        this.currentState = gameState.currentState();
        this.board = gameState.boardDto();
    }

    public String currentState() {
        return currentState;
    }

    public BoardDto board() {
        return board;
    }
}
