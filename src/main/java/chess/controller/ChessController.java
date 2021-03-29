package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final ChessGame chessGame;
    private final Commands commands;

    public ChessController() {
        this.chessGame = new ChessGame();
        this.commands = Commands.validCommands();
    }

    public void run() {
        OutputView.printStartMessage();
        while (chessGame.runnable()) {
            executeByCommand();
        }
    }

    private void executeByCommand() {
        try {
            OutputView.printRequestCommandMessage();
            String[] splitCommand = InputView.command().split(" ");
            String firstCommand = splitCommand[0];
            Command command = commands.findCommandByText(firstCommand);
            command.execute(chessGame, splitCommand);
            showResultByCommand(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            executeByCommand();
        }
    }

    private void showResultByCommand(Command command) {
        if (command.isMustShowBoard()) {
            OutputView.printChessBoard(chessGame.getPiecesByAllPosition());
        }
        if (command.isMustShowStatus()) {
            OutputView.printStatus(chessGame.scoreStatus());
        }
    }
}
