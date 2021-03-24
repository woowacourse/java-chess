package chess.controller;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Menu;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        OutputView.getNewGameCommand();
        firstCommand();
        Menu menu;
        do {
            menu = getMenu();
        } while (!chessManager.isEnd() && !menu.isEnd());

        OutputView.printGameResult(chessManager.calculateStatus());
    }

    private void firstCommand() {
        Menu menu = initFirstCommand();
        if (menu.isStart()) {
            OutputView.printBoard(chessManager.getBoard());
        }
        if (menu.isEnd()) {
            OutputView.printEndGame();
        }
    }

    private Menu initFirstCommand() {
        try {
            String userInput = InputView.getUserCommand();
            Menu menu = Menu.of(userInput);
            menu.isFirstCommand();
            return menu;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private Menu getMenu() {
        try {
            return executeMenu(InputView.getUserCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return getMenu();
        }
    }

    private Menu executeMenu(final String input) {
        final Menu menu = Menu.of(input);

        if (menu.isStart()) {
            restartGame();
        }

        if (menu.isMove()) {
            movePiece(MoveCommand.of(input));
        }

        if (menu.isShow()) {
            showAblePositionToMove(ShowCommand.of(input));
        }

        if (menu.isStatus()) {
            OutputView.printStatus(chessManager.calculateStatus());
        }

        return menu;
    }

    private void restartGame() {
        chessManager.resetBoard();
        OutputView.printBoard(chessManager.getBoard());
    }

    private void movePiece(final MoveCommand command) {
        chessManager.move(command);
        OutputView.printBoard(chessManager.getBoard());
    }

    private void showAblePositionToMove(final ShowCommand command) {
        OutputView.printAbleToMove(chessManager.getBoard(), chessManager.getReachablePositions(command));
    }
}
