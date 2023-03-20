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

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int ORIGIN_INDEX = 1;
    private static final int DEST_INDEX = 2;
    private final OutputView outputView;
    private final InputView inputView;
    private Board board;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printInitialMessage();
        while (true) {
            RequestInfo requestInfo = repeat(() -> new RequestInfo(inputView.inputGameCommand()));
            if (requestInfo.gameCommand == GameCommand.END) {
                return;
            }
            if (requestInfo.gameCommand == GameCommand.START) {
                gameStart();
                return;
            }
            outputView.printUnsuitableCommand();
        }
    }

    private void gameStart() {
        board = new Board();
        printBoard();
        while (true) {
            RequestInfo requestInfo = repeat(() -> new RequestInfo(inputView.inputGameCommand()));
            if (requestInfo.gameCommand == GameCommand.END) {
                return;
            }
            if (requestInfo.gameCommand == GameCommand.MOVE) {
                move(requestInfo.input);
                continue;
            }
            outputView.printUnsuitableCommand();
        }
    }

    private void move(String input) {
        List<String> command = Arrays.asList(input.split(" "));
        try {
            board.movePiece(makePosition(command.get(ORIGIN_INDEX)), makePosition(command.get(DEST_INDEX)));
        } catch (IllegalPieceMoveException e) {
            outputView.printError(e);
        }
        printBoard();
    }

    private Position makePosition(String input) {
        return Position.of(input.charAt(FILE_INDEX), input.charAt(RANK_INDEX));
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

    private static class RequestInfo {
        private final GameCommand gameCommand;
        private final String input;

        private RequestInfo(String input) {
            gameCommand = GameCommand.from(input);
            this.input = input;
        }
    }
}
