package chess.console;

import chess.console.state.Ready;
import chess.console.state.State;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.generator.BoardGenerator;

public class ChessGame {

    private final BoardGenerator boardGenerator;

    public ChessGame(BoardGenerator boardGenerator) {
        this.boardGenerator = boardGenerator;
    }

    public void init() {
        OutputView.printGuideMessage();
        Board board = boardGenerator.create();
        State state = new Ready(board);

        while (!state.isEnd()) {
            check(board);
            state = play(state);
            printBoard(board, state);
        }
    }

    private void check(Board board) {
        try {
            if (!board.isEmpty() && board.check()) {
                OutputView.printCheck();
            }
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
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

    private void printBoard(Board board, State state) {
        if (state.isStart()) {
            OutputView.printBoard(board.getBoard());
        }
    }
}
