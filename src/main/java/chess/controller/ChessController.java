package chess.controller;

import chess.model.command.Command;
import chess.model.command.End;
import chess.model.command.Move;
import chess.model.command.Start;
import chess.model.command.Status;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        State state = new Ready();
        outputView.printGameRule();
        while (!state.isFinished()) {
            state = proceed(state);
            printBoardIn(state);
            printScoreIn(state);
        }
    }

    private State proceed(final State state) {
        try {
            final Command command = createCommand(inputView.inputCommand());
            return state.execute(command);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return proceed(state);
        }
    }

    private Command createCommand(final String command) {
        if (command.equals("start")) {
            return new Start();
        }
        if (command.equals("end")) {
            return new End();
        }
        if (command.startsWith("move")) {
            return new Move(command);
        }
        if (command.equals("status")) {
            return new Status();
        }
        throw new IllegalArgumentException("[ERROR] 올바른 명령어를 입력 해주세요.");
    }

    private void printBoardIn(final State state) {
        if (!state.isFinished()) {
            outputView.printBoard(state.getBoard());
        }
    }

    private void printScoreIn(final State state) {
        if (state.isSleep()) {
            outputView.printScores(state.getScores());
        }
    }
}
