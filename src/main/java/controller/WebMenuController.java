package controller;

import domain.ChessGame;
import domain.menu.Menu;
import dto.ResultDto;

public class WebMenuController {
    public ResultDto run(String command, ChessGame game) {
        String menuButton = command.split(" ")[0];
        Menu menu = Menu.findMenu(menuButton);
        return startMenu(command, game, menu);
    }

    private ResultDto startMenu(String command, ChessGame game, Menu menu) {
        try {
            return new ResultDto(menu.executeWebMenu(command, game), "");
        } catch (RuntimeException e) {
            return new ResultDto(null, e.getMessage());
        }
    }
}
