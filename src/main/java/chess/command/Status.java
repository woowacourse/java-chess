package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        OutputView.printStatus(chessGame.createStatus());
    }
}
