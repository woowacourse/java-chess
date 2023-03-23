package chess.controller;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.command.CommandManager;
import chess.domain.command.Commands;
import chess.domain.piece.Piece;
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
            printBoardOrGameResultStep();
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

    private void printBoardOrGameResultStep() {
        if (!commandManager.canPrintGameResult()) {
            printBoardStep();
        }
        if (commandManager.canPrintGameResult()) {
            printGameResultStep();
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

    private void printGameResultStep() {
        ScoreBySideDto scoreBySideDto = commandManager.getScoreBySide();
        GameResultBySideDto gameResultBySideDto = commandManager.getGameResultBySide();
        OutputView.printScoreBySide(scoreBySideDto);
        OutputView.printGameResultBySide(gameResultBySideDto);
    }
}
