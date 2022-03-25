package chess.state;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.view.OutputView;

public class Ready implements State {

    @Override
    public State start() {
        final Board board = Board.create();
        OutputView.printBoard(board.getBoard());
        return new Started(Color.WHITE, board);
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final String[] commands) {
        OutputView.printErrorMessage("[ERROR] start를 하지 않아 move 할 수 없습니다.");
        return new Ready();
    }

    @Override
    public State status() {
        OutputView.printErrorMessage("[ERROR] start를 하지 않아 status 할 수 없습니다.");
        return new Ready();
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
