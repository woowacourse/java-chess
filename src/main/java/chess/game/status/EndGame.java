package chess.game.status;

import chess.domain.board.Board;
import chess.view.output.OutputView;

public class EndGame implements GameStatus {
    private final Board board;

    public EndGame(Board board) {
        this.board = board;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        OutputView.printWinner(board.getWinnerColor());
        return new TerminateGame();
    }
}
