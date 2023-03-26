package chess.controller;

import chess.controller.action.*;
import chess.domain.chessgame.ChessGame;

import java.util.Arrays;

public enum GameState {
    READY("start", new Start()),
    RUNNING("move", new Move()),
    CHECKING("status", new Status()),
    END("end", new End());

    private final String commandHead;
    private final GameAction gameAction;

    GameState(final String commandHead, final GameAction gameAction) {
        this.commandHead = commandHead;
        this.gameAction = gameAction;
    }

    public static GameState valueOfCommand(final String input) {
        return Arrays.stream(values())
                .filter(gameState -> gameState.commandHead.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다. 입력 값 :" + input));
    }

    public ChessGame execute(final Command command, final ChessGame chessGame) {
        return this.gameAction.execute(command, chessGame);
    }
}
