package controller;

import domain.ChessGame;
import dto.MenuDto;
import domain.menu.Menu;

public class WebMenuController {
    String errorMessage = "";

    public MenuDto run(String command, ChessGame game) {
        String menuButton = command.split(" ")[0];
        Menu menu = Menu.findMenu(menuButton);
        return startMenu(command, game, menu);
    }

    private MenuDto startMenu(String command, ChessGame game, Menu menu) {
        try {
            MenuDto menuDto = menu.execute(command, game);
            errorMessage = "";
            return menuDto;
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();
        }
        return null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
