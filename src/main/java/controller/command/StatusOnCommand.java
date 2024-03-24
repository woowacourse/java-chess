package controller.command;

import domain.board.ChessBoard;
import domain.board.Score;
import view.OutputView;

public class StatusOnCommand implements Command{
    @Override
    public void execute(final ChessBoard board, final OutputView outputView) {
        Score score = board.calculateScore();
        outputView.printScore(score);
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
