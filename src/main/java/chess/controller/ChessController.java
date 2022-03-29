package chess.controller;

import chess.domain.ChessGame;
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
        final ChessGame chessGame = new ChessGame(chessBoard);
        playTurn(chessGame);
    }

    private void playTurn(final ChessGame chessGame) {
        if (chessGame.isEnd()) {
            OutputView.printResult(chessGame.calculateScore());
            return;
        }
        final String commandText = InputView.requestCommand();
        final Command command = Command.from(commandText);
        if (command.equals(Command.END)) {
            return;
        }
        executeCommand(chessGame, commandText);
    }

    private void executeCommand(final ChessGame chessGame, final String commandText) {
        final Command command = Command.from(commandText);
        if (command.equals(Command.START)) {
            runStartCommand(chessGame);
        }
        if (command.equals(Command.MOVE)) {
            runMoveCommand(commandText, chessGame);
        }
        if (command.equals(Command.STATUS)) {
            runStatusCommand(chessGame);
        }
    }

    private void runStartCommand(final ChessGame chessGame) {
        try {
            checkBeforeStart(chessGame);
            chessGame.start();
            OutputView.printChessBoard(chessGame.findAllPiece());
            playTurn(chessGame);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessGame);
        }
    }

    private void checkBeforeStart(final ChessGame chessBoard) {
        if (chessBoard.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    private void runMoveCommand(final String inputText, final ChessGame chessGame) {
        try {
            checkBeforePlaying(chessGame);
            chessGame.move(
                    Position.from(Command.getFromPosition(inputText)),
                    Position.from(Command.getToPosition(inputText)));
            OutputView.printChessBoard(chessGame.findAllPiece());
            playTurn(chessGame);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessGame);
        }
    }

    private void runStatusCommand(final ChessGame chessGame) {
        try {
            checkBeforePlaying(chessGame);
            OutputView.printStatus(chessGame.calculateScore());
            playTurn(chessGame);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playTurn(chessGame);
        }
    }

    private void checkBeforePlaying(final ChessGame chessGame) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }
}
