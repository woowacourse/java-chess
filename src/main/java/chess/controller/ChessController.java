package chess.controller;

import chess.domain.MoveCommand;
import chess.manager.Menu;
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

        while (true) {
            MoveCommand moveCommand = MoveCommand.of(InputView.getUserMoveCommand());
            chessManager.readCommand(moveCommand);
            OutputView.printInitialBoard(chessManager.getBoard());
        }
    }
}
