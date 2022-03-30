package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements Command {
    protected Status() {
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printScore(chessGame.scoreOfWhite(), chessGame.scoreOfBlack());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
