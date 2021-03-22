package controller;

import domain.dto.BoardDto;
import domain.dto.MenuDto;
import domain.dto.StatusDto;
import domain.exception.CannotStartException;
import domain.exception.GameNotStartException;
import domain.exception.ImmovableSamePositionException;
import domain.exception.InvalidMoveException;
import domain.menu.Menu;
import domain.ChessGame;
import view.OutputView;

import java.util.NoSuchElementException;

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
        } catch (CannotStartException e) {
            OutputView.alreadyStartGame();
        } catch (GameNotStartException e) {
            OutputView.gameNotStart();
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            OutputView.invalidInputPosition(command);
        } catch (InvalidMoveException e) {
            OutputView.cannotMovePosition(command);
        } catch (ImmovableSamePositionException e) {
            OutputView.cannotMovesamePosition(command);
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
