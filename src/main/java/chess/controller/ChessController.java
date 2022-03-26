package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();

        final ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        playTurn(chessBoard);
    }

    private void playTurn(final ChessBoard chessBoard) {
        if (chessBoard.isEnd()) {
            OutputView.printResult(chessBoard.calculateScore());
            return;
        }
        final String commandText = InputView.requestCommand();
        final Command command = Command.splitCommand(commandText);
        if (command.equals(Command.END)) {
            return;
        }
        executeCommand(chessBoard, commandText);
    }

    private void executeCommand(final ChessBoard chessBoard, final String commandText) {
        final Command command = Command.splitCommand(commandText);
        if (command.equals(Command.START)) {
            runStartCommand(chessBoard);
        }
        if (command.equals(Command.MOVE)) {
            runMoveCommand(commandText, chessBoard);
        }
        if (command.equals(Command.STATUS)) {
            runStatusCommand(chessBoard);
        }
    }

    private void runStartCommand(final ChessBoard chessBoard) {
        try {
            checkBeforeStart(chessBoard);
            chessBoard.start();
            OutputView.printChessBoard(chessBoard);
            playTurn(chessBoard);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessBoard);
        }
    }

    private void checkBeforeStart(final ChessBoard chessBoard) {
        if (chessBoard.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    private void runMoveCommand(final String inputText, final ChessBoard chessBoard) {
        try {
            checkBeforePlaying(chessBoard);
            chessBoard.move(
                    Position.from(Command.getFromPosition(inputText)),
                    Position.from(Command.getToPosition(inputText)));
            OutputView.printChessBoard(chessBoard);
            playTurn(chessBoard);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessBoard);
        }
    }

    private void runStatusCommand(final ChessBoard chessBoard) {
        try {
            checkBeforePlaying(chessBoard);
            OutputView.printStatus(chessBoard.calculateScore());
            playTurn(chessBoard);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessBoard);
        }
    }

    private void checkBeforePlaying(final ChessBoard chessBoard) {
        if (chessBoard.isReady()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }
}
