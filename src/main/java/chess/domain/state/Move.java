package chess.domain.state;

import static chess.console.view.InputView.FROM_POSITION_INDEX;
import static chess.console.view.InputView.TO_POSITION_INDEX;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class Move extends Running {

    private final Board board;

    protected Move(Board board) {
        super(board);
        this.board = board;
    }

    @Override
    public State run(List<String> inputs) {
        if (board.isEmpty()) {
            throw new IllegalStateException("체스 게임을 시작해야 합니다.");
        }

        board.move(Position.of(inputs.get(FROM_POSITION_INDEX)), Position.of(inputs.get(TO_POSITION_INDEX)));
        return new Running(board);
    }
}
