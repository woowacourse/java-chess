package chess.console.state;

import static chess.console.view.InputView.MENU_INDEX;

import chess.console.view.OutputView;
import chess.domain.board.Board;

public class Ready implements State {

    private final Board board;

    public Ready(Board board) {
        this.board = board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(String[] inputs) {
        Command command = Command.of(inputs[MENU_INDEX]);

        if (command.isEnd()) {
            return new End();
        }

        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작하지 않았습니다.");
        }

        OutputView.printBoard(board.getBoard());
        return new Running(board);
    }
}
