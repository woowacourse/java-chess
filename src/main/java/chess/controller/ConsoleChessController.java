package chess.controller;

import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.MoveCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController {
    private Board board;

    private static Command resolveCommand() {
        try {
            return Command.of(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Command.ERROR;
        }
    }

    public static void runGame(Board board) {
        while (!board.isGameOver()) {
            OutputView.printTurn(board.getTeam());

            try {
                MoveCommand moveCommand = new MoveCommand(InputView.requestCommand());
                board.move(moveCommand);
                OutputView.printBoard(board);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void spreadBoard() {
        this.board = new Board();
        OutputView.printBoard(this.board);
    }

    public void run() {
        OutputView.printGameCommand();

        Command runCommand;
        do {
            runCommand = resolveCommand();
        } while (runCommand.isError());

        if (runCommand.isStart()) {
            spreadBoard();
            runGame(this.board);

            OutputView.printStatusMessage();
            Command statusCommand;
            do {
                statusCommand = resolveCommand();
            } while (statusCommand.isError());

            if (statusCommand.isStatus()) {
                GameResult gameResult = this.board.createGameResult();
                OutputView.printResult(gameResult);
            }
        }
    }

}
