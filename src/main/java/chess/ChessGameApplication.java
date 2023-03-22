package chess;

import chess.controller.Controller;
import chess.domain.state.CommandProcessor;
import chess.domain.state.StateProcessor;

public final class ChessGameApplication {
    public static void main(String[] args) {
        CommandProcessor commandProcessor = CommandProcessor.create();
        StateProcessor stateProcessor = StateProcessor.create();
        Controller controller = new Controller(commandProcessor, stateProcessor);
        controller.run();
    }
}
