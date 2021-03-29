package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public ChessController() {
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printStartMessage();
        while (chessGame.runnable()) {
            executeByCommand(chessGame);
        }
    }

    private void executeByCommand(ChessGame chessGame) {
        try {
            OutputView.printRequestCommandMessage();
            String[] splitCommand = InputView.command().split(" ");
            String firstCommand = splitCommand[0];
            Commands commands = Commands.validCommands();
            Command command = commands.findCommandByText(firstCommand);
            command.execute(chessGame, splitCommand);
            showResultByCommand(command, chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            executeByCommand(chessGame);
        }
    }

    private void showResultByCommand(Command command, ChessGame chessGame) {
        if (command.isMustShowBoard()) {
            OutputView.printChessBoard(chessGame.getPiecesByAllPosition());
        }
        if (command.isMustShowStatus()) {
            OutputView.printStatus(chessGame.scoreStatus());
        }
    }
}
