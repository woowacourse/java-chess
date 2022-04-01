package chess.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.start();
        OutputView.printBoard(chessGame.getBoard());
    }
}
