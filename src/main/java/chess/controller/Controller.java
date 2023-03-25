package chess.controller;

import chess.domain.Color;
import chess.domain.state.StateProcessor;
import chess.dto.CommandDto;
import chess.dto.ScoreDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public final class Controller {
    private final CommandProcessor commandProcessor;
    private final StateProcessor stateProcessor;
    private final ResultProcessor resultProcessor;

    public Controller(final CommandProcessor commandProcessor, final StateProcessor stateProcessor, final ResultProcessor resultProcessor) {
        this.commandProcessor = commandProcessor;
        this.stateProcessor = stateProcessor;
        this.resultProcessor = resultProcessor;
    }

    public void run() {
        OutputView.printStartMessage();

        setup();

        while (stateProcessor.isNotEnd()) {
            retryOnError(() -> {
                printStateInformation();
                CommandDto command = InputView.inputGameState();
                commandProcessor.execute(stateProcessor, command);
                resultProcessor.execute(command);
            });
        }
    }

    private void setup() {
        commandProcessor.register(Command.START, command -> stateProcessor.start());
        commandProcessor.register(Command.END, command -> stateProcessor.end());
        commandProcessor.register(Command.STATUS, command -> stateProcessor.identity());
        commandProcessor.register(Command.MOVE, stateProcessor::move);

        resultProcessor.register(Command.STATUS, this::printStatus);
        resultProcessor.register(Command.END, OutputView::printEnd);
        resultProcessor.register(Command.START, () -> OutputView.printChessBoard(stateProcessor.getBoard()));
        resultProcessor.register(Command.MOVE, () -> OutputView.printChessBoard(stateProcessor.getBoard()));
    }

    private void printStatus() {
        if (stateProcessor.isGameEnd()) {
            OutputView.printScore(ScoreDto.of(stateProcessor.status(Color.BLACK), stateProcessor.status(Color.WHITE), stateProcessor.getColor()));
        }
        if (stateProcessor.isNotGameEnd()) {
            OutputView.printScore(ScoreDto.of(stateProcessor.status(Color.BLACK), stateProcessor.status(Color.WHITE)));
        }
    }

    private void printStateInformation() {
        if (stateProcessor.getColor() != Color.EMPTY && stateProcessor.isNotGameEnd()) {
            OutputView.printColor(stateProcessor.getColor());
        }
        if (stateProcessor.isGameEnd()) {
            OutputView.printGameEnd();
        }
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
