package chess.controller;

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
        State state = initState();
        outputView.printGameRule();
        while (!state.isFinished()) {
            state = proceed(state);
            outputView.printBoard(state.getBoard());
            printScoreIn(state);
        }
    }

    private State initState() {
        try {
            return new Ready();
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return initState();
        }
    }

    private State proceed(State state) {
        try {
            Command command = createCommand(inputView.inputCommand());
            return state.execute(command);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return proceed(state);
        }
    }

    private Command createCommand(String command) {
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

    private void printScoreIn(State state) {
        if (state.isSleep()) {
            outputView.printScores(state.getScores());
        }
    }
}
