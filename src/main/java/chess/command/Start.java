package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start implements Command {
    protected Start() {
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.start();
        outputView.printBoard(chessGame.getBoard().getValue());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
