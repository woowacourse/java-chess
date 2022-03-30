package chess.domain.state;

import static chess.console.view.InputView.COMMAND_INDEX;

import chess.domain.board.Board;
import java.util.List;

public class Ready implements State {

    private boolean isStart = false;
    private final Board board;

    public Ready(Board board) {
        this.board = board;
    }

    @Override
    public boolean isStart() {
        return isStart;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(List<String> inputs) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        if (command.isEnd()) {
            return new End();
        }

        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작하지 않았습니다.");
        }

        isStart = true;
        return new Running(board);
    }
}
