package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.startGame();
        OutputView.printChessBoard(chessGame.getChessBoard());
    }
}
