package chess.controller;

import chess.domain.state.CommandProcessor;
import chess.domain.state.StateProcessor;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public final class Controller {
    private final CommandProcessor commandProcessor;
    private final StateProcessor stateProcessor;

    public Controller(final CommandProcessor commandProcessor, final StateProcessor stateProcessor) {
        this.commandProcessor = commandProcessor;
        this.stateProcessor = stateProcessor;
    }


    public void run() {
        OutputView.printStartMessage();

        setup();

        while (stateProcessor.isNotEnd()) {
            retryOnError(() -> commandProcessor.execute(stateProcessor, InputView.inputGameState()));
            OutputView.printChessBoard(stateProcessor.getBoard());
        }
    }

    private void setup() {
        commandProcessor.register(Command.START, command -> stateProcessor.start());
        commandProcessor.register(Command.END, command -> stateProcessor.end());
        commandProcessor.register(Command.MOVE, stateProcessor::move);
    }

    private void retryOnError(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
            retryOnError(runnable);
        }
    }
}
