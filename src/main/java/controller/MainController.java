package controller;

import domain.ChessGame;
import domain.state.Ready;
import domain.state.State;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {

    private final ChessGame chessGame;

    public MainController() {
        this.chessGame = new ChessGame();
    }

    public MainController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        InputView.printStartMessage();
        State state = new Ready(chessGame);

        while (!state.isEnd()) {
            state = play(state);
        }
    }

    public State play(State state) {
        try {
            List<String> input = InputView.readCommand();
            return state.run(input);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e);
            return state;
        }
    }
}
