package controller;

import domain.ChessGame;
import dto.BoardDto;
import dto.MenuDto;
import dto.StatusDto;
import domain.menu.Menu;
import view.OutputView;

public class MenuController {
    String errorMessage = "";

    public void run(String command, ChessGame game) {
        String menuButton = command.split(" ")[0];
        Menu menu = Menu.findMenu(menuButton);
        startMenu(command, game, menu);
    }

    private void startMenu(String command, ChessGame game, Menu menu) {
        try {
            MenuDto menuDto = menu.execute(command, game);
            selectOutputView(menuDto);
            errorMessage = "";
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();
            OutputView.printError(e.getMessage());
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void selectOutputView(MenuDto menuDto) {
        if (menuDto.isBoardDto()) {
            OutputView.showBoard((BoardDto) menuDto);
            return;
        }
        OutputView.showStatus((StatusDto) menuDto);
    }
}
