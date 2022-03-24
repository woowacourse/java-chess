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
        String[] command = InputView.requestCommand();
        if (command[0].equals(Command.END.getValue())) {
            return;
        }

        if (command[0].equals(Command.START.getValue())) {
            chessBoard = checkGameStatus(chessBoard);
            playTurn(chessBoard);
        }

        if (command[0].equals(Command.MOVE.getValue())) {
            runMoveCommand(command, chessBoard);
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

    private void runMoveCommand(String[] command, ChessBoard chessBoard) {
        try {
            if (chessBoard == null) {
                throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
            }
            chessBoard.move(new Position(command[1]), new Position(command[2]));
            OutputView.printChessBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
