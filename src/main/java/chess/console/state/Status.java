package chess.console.state;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;

public class Status implements State {

    private final Board board;

    public Status(Board board) {
        this.board = board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(String[] inputs) {
        Score score = board.createResult();
        OutputView.printStatus(score.getValue(), score.findWinTeam());
        return new Running(board);
    }
}
