package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Menu;
import chess.manager.ChessManager;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static void main(String[] args) {
        new ChessController().run();
    }

    public void run() {
        if(Menu.isEnd(InputView.getNewGameCommand())){
            return;
        }

        ChessManager chessManager = new ChessManager();
        OutputView.printInitialBoard(chessManager.getBoard());
    }
}
