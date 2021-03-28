package controller;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.dto.StatusDto;
import domain.menu.Menu;
import view.OutputView;

public class MenuController {
    public void run(String command, ChessGame game) {
        String menuButton = command.split(" ")[0];
        Menu menu = Menu.findMenu(menuButton);
        startMenu(command, game, menu);
    }

    private void startMenu(String command, ChessGame game, Menu menu) {
        try {
            MenuDto menuDto = menu.execute(command, game);
            selectOutputView(menuDto);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void selectOutputView(MenuDto menuDto) {
        if (menuDto.isBoardDto()) {
            OutputView.showBoard((BoardDto) menuDto);
            return;
        }
        OutputView.showStatus((StatusDto) menuDto);
    }
}
