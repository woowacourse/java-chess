package chess.controller;

import chess.Board;
import chess.Command;
import chess.CommandException;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final Board board = Board.createInitialBoard();

    public void run() {
        start();
        while (board.isNotCheckmate()) {
            play();
        }
    }

    private void start() {
        OutputView.printGameIntro();
        requestCommand();
        OutputView.printBoard(board);
    }

    private void requestCommand() {
        try {
            Command command = Command.beforeGameCommandOf(InputView.requestCommand());
            if (command.isEnd()) {
                System.exit(0);
            }
        } catch (CommandException e) {
            OutputView.printExceptionMessage(e.getMessage());
            requestCommand();
        }
    }

    private void play() {
        try {
            Command command = Command.inGameCommandOf(InputView.requestCommand());
            if (command.isMove()) {
                Position startPosition = Position.of(InputView.requestPosition());
                Position endPosition = Position.of(InputView.requestPosition());
                board.moveIfPossible(startPosition, endPosition);
                OutputView.printBoard(board);
            } else if (command.isStatus()) {
                OutputView.printScores(board.calculateScores());
            }
        } catch (CommandException | IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            play();
        }
    }
}
