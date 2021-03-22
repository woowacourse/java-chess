package controller;

import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.dto.StatusDto;
import domain.exception.CannotStartException;
import domain.exception.GameNotStartException;
import domain.menu.Menu;
import domain.ChessGame;
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
        } catch (CannotStartException e) {  // 메뉴를 시작할 수 없는 경우
            OutputView.alreadyStartGame();
        } catch (GameNotStartException e) { // 메뉴가 시작되지 않아 움직일 수 없는 경우
            OutputView.gameNotStart();
        }
    }

    private void selectOutputView(MenuDto menuDto) {
        if (menuDto instanceof BoardDto) {
            OutputView.showBoard((BoardDto) menuDto);
            return;
        }
        OutputView.showStatus((StatusDto) menuDto);
    }
}
