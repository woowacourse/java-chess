package chess;

import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.MoveCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static Board board;

    private static Command resolveCommand() {
        try {
            return Command.of(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Command.ERROR;
        }
    }

    private static void spreadBoard() {
        board = new Board();
        OutputView.printBoard(board);
    }

    public static void runGame() {
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

    public void run() {
        OutputView.printGameCommand();

        Command runCommand;
        do {
            runCommand = resolveCommand();
        } while (runCommand.isError());

        if (runCommand.isStart()) {
            spreadBoard();
            runGame();

            OutputView.printStatusMessage();
            Command statusCommand;
            do {
                statusCommand = resolveCommand();
            } while (statusCommand.isError());

            if (statusCommand.isStatus()) {
                GameResult gameResult = board.createGameResult();
                OutputView.printResult(gameResult);
            }
        }
    }

}
