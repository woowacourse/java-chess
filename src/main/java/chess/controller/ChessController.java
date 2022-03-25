package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        playTurn(new ChessBoard());
    }

    private void playTurn(final ChessBoard chessBoard) {
        if (chessBoard.isEnd()) {
            OutputView.printResult(chessBoard.calculateScore());
            return;
        }

        final String text = InputView.requestCommand();
        final Command command = Command.splitCommand(text);
        if (command == Command.END) {
            return;
        }

        if (command == Command.START) {
            runStartCommand(chessBoard);
            playTurn(chessBoard);
        }

        if (command == Command.MOVE) {
            runMoveCommand(text, chessBoard);
            playTurn(chessBoard);
        }

        if (command == Command.STATUS) {
            runStatusCommand(chessBoard);
            playTurn(chessBoard);
        }
    }

    private void runStartCommand(final ChessBoard chessBoard) {
        try {
            checkBeforeStart(chessBoard);
            chessBoard.start();
            OutputView.printChessBoard(chessBoard);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
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
            chessBoard.move(Position.from(Command.getFromPosition(inputText)),
                    Position.from(Command.getToPosition(inputText)));
            OutputView.printChessBoard(chessBoard);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void runStatusCommand(final ChessBoard chessBoard) {
        try {
            checkBeforePlaying(chessBoard);
            OutputView.printStatus(chessBoard.calculateScore());
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforePlaying(final ChessBoard chessBoard) {
        if (chessBoard.isReady()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }
}
