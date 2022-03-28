package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements Command {
    protected Status() {
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
