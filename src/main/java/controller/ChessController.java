package controller;

import domain.menu.Menu;
import domain.ChessGame;
import domain.exception.InvalidMenuException;
import view.InputView;
import view.OutputView;

public class ChessController {
    private MenuController menuController = new MenuController();

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.showGuide();
        while (!chessGame.isEnd()) {
            selectMenu(chessGame);
        }
        OutputView.showEndMessage();
    }

    private Menu selectMenu(ChessGame chessGame) {
        try {
            OutputView.showCommandLine();
            String command = InputView.inputCommand();
            menuController.run(command, chessGame);
            String menuCommand = command.split(" ")[0];
            Menu menu = Menu.findMenu(menuCommand);
            return menu;
        } catch (InvalidMenuException e) {
            System.out.println("메뉴를 잘못 입력하였습니다.");
            return selectMenu(chessGame);
        }
    }
}
