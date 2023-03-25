package chess;

import chess.controller.CommandProcessor;
import chess.controller.Controller;
import chess.controller.ResultProcessor;
import chess.domain.state.StateProcessor;

public final class ChessGameApplication {
    public static void main(String[] args) {
        CommandProcessor commandProcessor = CommandProcessor.create();
        StateProcessor stateProcessor = StateProcessor.create();
        ResultProcessor resultProcessor = ResultProcessor.create();

        Controller controller = new Controller(commandProcessor, stateProcessor, resultProcessor);
        controller.run();
    }
}
