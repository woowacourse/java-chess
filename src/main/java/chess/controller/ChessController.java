package chess.controller;

import chess.domain.game.board.ChessBoard;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.game.board.ChessBoardFactory.initBoard;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        playTurn(new ChessBoard(initBoard()));
    }

    private void playTurn(ChessBoard chessBoard) {
        if (chessBoard.isEnd()) {
            OutputView.printResult(chessBoard.calculateScore(), chessBoard.decideWinner());
            return;
        }

        String text = InputView.requestCommand();
        Command command = Command.splitCommand(text);

        if (command == Command.END) {
            return;
        }

        readyGame(chessBoard, command);
        playingGame(chessBoard, text, command);
    }

    private void readyGame(ChessBoard chessBoard, Command command) {
        if (command == Command.START) {
            runStartCommand(chessBoard);
            playTurn(chessBoard);
        }
    }

    private void playingGame(ChessBoard chessBoard, String text, Command command) {
        if (command == Command.MOVE) {
            runMoveCommand(chessBoard, text);
            playTurn(chessBoard);
        }

        if (command == Command.STATUS) {
            runStatusCommand(chessBoard);
            playTurn(chessBoard);
        }
    }

    private void runStartCommand(ChessBoard chessBoard) {
        try {
            checkBeforeStart(chessBoard);
            chessBoard.start();
            OutputView.printChessBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforeStart(ChessBoard chessBoard) {
        if (chessBoard.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    private void runMoveCommand(ChessBoard chessBoard, String text) {
        try {
            String from = Command.getFromPosition(text);
            String to = Command.getToPosition(text);
            checkBeforePlaying(chessBoard);
            chessBoard.move(new Position(from), new Position(to));
            OutputView.printChessBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void runStatusCommand(ChessBoard chessBoard) {
        try {
            checkBeforePlaying(chessBoard);
            OutputView.printStatus(chessBoard.calculateScore());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforePlaying(ChessBoard chessBoard) {
        if (chessBoard.isReady()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }
}
