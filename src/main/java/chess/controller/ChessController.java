package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        ChessBoard chessBoard = null;
        String[] command = InputView.requestCommand();
        while (!command[0].equals(Command.END.getValue())) {
            if (command[0].equals(Command.START.getValue())) {
                chessBoard = runStartCommand(command);
            }
            if (command[0].equals(Command.MOVE.getValue())) {
                runMoveCommand(command, chessBoard);
            }
            command = InputView.requestCommand();
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

    private ChessBoard runStartCommand(String[] command) {
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard);
        return chessBoard;
    }
}
