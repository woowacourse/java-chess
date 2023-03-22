package chess.controller;

import java.util.List;

import chess.gamecommand.CommandManager;
import chess.gamecommand.Commands;
import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final CommandManager commandManager;

    public ChessController(final InputView inputView) {
        this.inputView = inputView;
        this.commandManager = new CommandManager();
    }

    public void run() {
        printInitMessage();
        while (!commandManager.isEnd()) {
            executeCommandStep();
            printBoardStep();
        }
    }

    private void printInitMessage() {
        OutputView.printGameStartMessage();
        OutputView.printGameCommandInputMessage();
    }

    private void executeCommandStep() {
        try {
            commandManager.execute(new Commands(inputGameCommand()));
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            executeCommandStep();
        }
    }

    private void printBoardStep() {
        if (!commandManager.isEnd()) {
            List<Piece> pieces = commandManager.getPieces();
            OutputView.printCurrentTurn(commandManager.getTurnDisplayName());
            NameBoard nameBoard = NameBoard.generateNameBoard(pieces);
            OutputView.printBoard(nameBoard.getNameBoard());
        }
    }

    private List<String> inputGameCommand() {
        try {
            return inputView.inputGameCommand();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputGameCommand();
        }
    }
}
