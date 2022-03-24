package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();

        playTurn(null);
    }

    private void playTurn(ChessBoard chessBoard) {
        String text = InputView.requestCommand();
        Command command = Command.splitCommand(text);
        if (command == Command.END) {
            return;
        }

        if (command == Command.START) {
            chessBoard = checkGameStatus(chessBoard);
            playTurn(chessBoard);
        }

        if (command == Command.MOVE) {
            String from = Command.getFromPosition(text);
            String to = Command.getToPosition(text);

            runMoveCommand(from, to, chessBoard);
            playTurn(chessBoard);
        }
    }

    private ChessBoard checkGameStatus(ChessBoard chessBoard) {
        try {
            checkGameStart(chessBoard != null);
            chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard);
            return chessBoard;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
        return chessBoard;
    }

    private void checkGameStart(boolean gameStarted) {
        if (gameStarted) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    private void runMoveCommand(String from, String to, ChessBoard chessBoard) {
        try {
            if (chessBoard == null) {
                throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
            }
            chessBoard.move(new Position(from), new Position(to));
            OutputView.printChessBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
