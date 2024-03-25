package controller;

import domain.command.Command;
import domain.command.CommandType;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        outputView.printCommandMessage();
        while (chessGame.isNotEnd()) {
            executeGame(chessGame);
        }
    }

    private void executeGame(ChessGame chessGame) {
        try {
            Command command = parseCommand();
            command.execute(chessGame);
            outputView.printChessBoard(chessGame.getChessBoard());
        } catch (final Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private Command parseCommand() {
        return CommandType.parse(inputView.enterChessCommand());
    }
}
