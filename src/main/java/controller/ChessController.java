package controller;

import controller.command.Command;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;
import view.command.CommandType;

public class ChessController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void runChessGame() {
        ChessGame chessGame = new ChessGame();
        outputView.printStartMessage();
        while (chessGame.isRunning()) {
            CommandType commandType = inputView.inputCommand();
            Command command = Command.from(commandType);
            command.execute(commandType, chessGame);

            outputView.printChessBoard(chessGame.getChessBoard());
        }
    }
}
