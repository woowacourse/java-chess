package controller;

import controller.menu.Menu;
import domain.ChessGame;
import domain.exception.InvalidMenuException;
import view.InputView;
import view.OutputView;

public class ChessController {
    private ChessGame chessGame;

    public void run() {
        chessGame = new ChessGame();
        OutputView.showGuide();
        while (!chessGame.isEnd()) {
            selectMenu();
        }
    }

    private Menu selectMenu() {
        try {
            OutputView.showCommandLine();
            String command = InputView.inputCommand();
            String menuCommand = command.split(" ")[0];
            Menu menu = Menu.findMenu(menuCommand);
            menu.execute(command, chessGame);
            return menu;
        } catch (InvalidMenuException e) {
            System.out.println("메뉴를 잘못 입력하였습니다.");
            return selectMenu();
        }
    }
}
