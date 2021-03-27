package chess.controller;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        OutputView.printGuideStartGame();
        firstCommand();
        Command command;
        do {
            command = getMenu();
        } while (!chessManager.isEnd() && !command.isEnd());

        OutputView.printGameResult(chessManager.getStatus());
    }

    private void firstCommand() {
        Command command = initFirstCommand();
        if (command.isStart()) {
            OutputView.printBoard(chessManager.getBoard());
        }
        if (command.isEnd()) {
            OutputView.printEndGame();
        }
    }

    private Command initFirstCommand() {
        try {
            String userInput = InputView.getUserCommand();
            Command command = Command.of(userInput);
            command.isFirstCommand();
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private Command getMenu() {
        try {
            return executeMenu(InputView.getUserCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return getMenu();
        }
    }

    private Command executeMenu(final String input) {
        final Command command = Command.of(input);

        if (command.isStart()) {
            restartGame();
        }

        if (command.isMove()) {
            movePiece(MoveCommand.of(input));
        }

        if (command.isShow()) {
            showAblePositionToMove(ShowCommand.of(input));
        }

        if (command.isStatus()) {
            OutputView.printStatus(chessManager.getStatus());
        }

        return command;
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
