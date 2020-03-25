package chess.controller.command;

import chess.domain.gamestatus.GameStatus;

public class Move implements Command {
    private String fromPosition;
    private String toPosition;

    public Move(String fromPosition, String toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        return gameStatus.move(fromPosition, toPosition);
    }
}