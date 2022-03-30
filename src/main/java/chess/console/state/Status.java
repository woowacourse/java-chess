package chess.console.state;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;
import java.util.List;

public class Status extends Running {

    private final Board board;

    protected Status(Board board) {
        super(board);
        this.board = board;
    }

    @Override
    public State run(List<String> inputs) {
        Score score = board.createResult();
        OutputView.printStatus(score.getValue(), score.findWinTeam());
        return new Running(board);
    }
}
