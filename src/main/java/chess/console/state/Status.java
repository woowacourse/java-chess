package chess.console.state;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;
import java.util.List;

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
    public State run(List<String> inputs) {
        Score score = board.createResult();
        OutputView.printStatus(score.getValue(), score.findWinTeam());
        return new Running(board);
    }
}
