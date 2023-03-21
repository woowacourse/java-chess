package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.strategy.StrategyCommand;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;


public class ChessController {

    private final ChessService chessService;
    private ChessState state;

    public ChessController() {
        this.chessService = new ChessService();
        this.state = ChessState.INIT;
    }

    public void run() {
        OutputView.printStart();
        while (state != ChessState.END) {
            state = play();
        }
    }

    private ChessState play() {
        try {
            StrategyCommand command = Command.bind(InputView.read());
            return command.execute(state, chessService);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return play();
        }
    }
}
