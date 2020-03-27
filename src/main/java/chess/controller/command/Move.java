package chess.controller.command;

import chess.domain.gamestate.GameState;

public class Move implements Command {
    private String fromPosition;
    private String toPosition;

    public Move(String fromPosition, String toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public GameState execute(GameState gameState) {
        return gameState.move(fromPosition, toPosition);
    }
}