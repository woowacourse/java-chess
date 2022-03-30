package chess.console.state;

import static chess.console.view.InputView.FROM_POSITION_INDEX;
import static chess.console.view.InputView.TO_POSITION_INDEX;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class Move implements State {

    private final Board board;

    public Move(Board board) {
        this.board = board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(List<String> inputs) {

        if (board.isEmpty()) {
            OutputView.printStartWarning();
            throw new IllegalStateException("게임을 시작 후 실행 가능합니다.");
        }

        try {
            board.move(Position.of(inputs.get(FROM_POSITION_INDEX)), Position.of(inputs.get(TO_POSITION_INDEX)));
            OutputView.printBoard(board.getBoard());

        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }

        return new Running(board);
    }
}
