package chess.controller;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Menu;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static void main(final String[] args) {
        new ChessController().run();
    }

    public void run() {
        if (!Menu.of(InputView.getNewGameCommand()).isStart()) {
            return;
        }

        ChessManager chessManager = new ChessManager();
        OutputView.printBoard(chessManager.getBoard());

        String userInput;
        Menu menu;
        do {
            userInput = InputView.getUserCommand();
            menu = Menu.of(userInput);

            if (menu.isMove()) {
                chessManager.move(MoveCommand.of(userInput));
                OutputView.printBoard(chessManager.getBoard());
            }

            if (menu.isShow()) {
                OutputView.printAbleToMove(chessManager.getBoard(), chessManager.getAbleToMove(ShowCommand.of(userInput)));
            }

            if (menu.isStatus()) {
                OutputView.printStatus(chessManager.calculateStatus());
            }
        } while (!chessManager.isEnd() && !menu.isEnd());

        OutputView.printGameResult(chessManager.calculateStatus());
    }
}