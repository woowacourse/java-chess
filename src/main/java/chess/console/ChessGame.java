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
        Board board = createBoard();
        State state = new Ready(board);

        while (!state.isEnd()) {
            if (check(board)) {
                OutputView.printCheck();
            }
            state = play(state);
        }
    }

    private Board createBoard() {
        Board board = new Board();
        board.initBoard(boardGenerator);
        return board;
    }

    private boolean check(Board board) {
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
