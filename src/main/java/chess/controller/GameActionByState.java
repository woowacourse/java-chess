package chess.controller;

import chess.controller.action.*;
import chess.domain.chessgame.ChessGame;

import java.util.Arrays;

public enum GameActionByState {
    START(GameState.READY, new Start()),
    MOVE(GameState.RUNNING, new Move()),
    STATUS(GameState.CHECKING, new Status()),
    END(GameState.END, new End());

    private final GameState gameState;
    private final GameAction gameAction;

    GameActionByState(final GameState gameState, final GameAction gameAction) {
        this.gameState = gameState;
        this.gameAction = gameAction;
    }

    public static GameActionByState valueOfCommand(final Command command) {
        return Arrays.stream(values())
                .filter(action -> action.gameState == command.getGameState())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public ChessGame execute(final Command command, final ChessGame chessGame) {
        return this.gameAction.execute(command, chessGame);
    }
}
