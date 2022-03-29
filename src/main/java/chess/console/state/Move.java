package chess.console.state;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.position.Position;

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
    public State run(String[] inputs) {

        if (board.isEmpty()) {
            OutputView.printStartWarning();
            throw new IllegalStateException("게임을 시작 후 실행 가능합니다.");
        }

        try {
            board.move(Position.of(inputs[1]), Position.of(inputs[2]));
            OutputView.printBoard(board.getBoard());

        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }

        return new Running(board);
    }
}
