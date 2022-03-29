package chess.console;

import chess.console.state.Ready;
import chess.console.state.State;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Board;

public class ChessGame {

    private final Board board = new Board();

    public void init() {
        OutputView.printGuideMessage();
        State state = new Ready();

        while (!state.isEnd()) {
            if (check()) {
                OutputView.printCheck();
            }
            state = play(state);
        }
    }

    private boolean check() {
        try {
            return !board.isEmpty() && board.check();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private State play(State state) {
        try {
            return state.run(InputView.inputCommand());
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return play(state);
        }
    }
}
