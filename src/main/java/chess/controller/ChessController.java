package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.exception.IllegalPieceMoveException;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final Board board = new Board();

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void realInit() {
        outputView.printInitialMessage();
        while (true) {
            CommandData gameCommand = repeat(() -> new CommandData(inputView.inputGameCommand()));
            if (gameCommand.gameCommand == GameCommand.END) {
                return;
            }
            if (gameCommand.gameCommand == GameCommand.START) {
                gameStart();
                return;
            }
            outputView.printUnsuitableCommand();
        }
    }

    private void gameStart() {
        printBoard();
        while (true) {
            CommandData gameCommand = repeat(() -> new CommandData(inputView.inputGameCommand()));
            if (gameCommand.gameCommand == GameCommand.END) {
                return;
            }
            if (gameCommand.gameCommand == GameCommand.MOVE) {
                move(gameCommand.input);
                continue;
            }
            outputView.printUnsuitableCommand();
        }
    }

    private void move(String input) {
        List<String> command = Arrays.asList(input.split(" "));
        try {
            board.movePiece(Position.of(command.get(1).substring(0, 1), command.get(1).substring(1, 2)), Position.of(command.get(2).substring(0, 1), command.get(2).substring(1, 2)));
        } catch (IllegalPieceMoveException e) {
            outputView.printError(e);
        }
        printBoard();
    }

    private void printBoard() {
        outputView.printBoard(board.getPiecePosition());
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e);
            }
        }
    }

    private static class CommandData {
        private final GameCommand gameCommand;
        private final String input;

        private CommandData(String input) {
            gameCommand = GameCommand.from(input);
            this.input = input;
        }
    }
}
