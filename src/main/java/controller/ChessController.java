package controller;

import dto.GameBoardDto;
import model.Camp;
import model.ChessGame;
import model.Command;
import model.menu.Init;
import model.menu.Menu;
import view.OutputView;

public class ChessController {

    private final InputController inputController;
    private final OutputView outputView;

    public ChessController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        outputView.printStartMessage();
        play2(chessGame);
    }

    private void play(final ChessGame chessGame) {
        Menu currentMenu = Init.gameSetting(inputController.getCommand());
        while (currentMenu.isRunning()) {
            printCurrentStatus(chessGame, chessGame.getCamp());
            currentMenu = currentMenu.play(inputController.getCommand(), chessGame);
        }
    }

    private void play2(final ChessGame chessGame) {
        Menu status = Command.of(inputController.getCommands());
        status.play2(chessGame);
        while (chessGame.isNotEnd()) {
            printCurrentStatus(chessGame, chessGame.getCamp());
            status = Command.of(inputController.getCommands());
            status.play2(chessGame);
        }
    }

    private void printCurrentStatus(final ChessGame chessGame, final Camp camp) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(camp);
    }
}
