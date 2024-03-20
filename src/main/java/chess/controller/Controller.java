package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public void run() {
        OutputView.printStartMessage();
        Command command = Command.getStartCommand(InputView.readCommand());
        ChessBoard chessBoard = ChessBoard.initializeChessBoard();
        while (!command.isEnd()) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
            command = Command.getProcessCommand(InputView.readCommand());
        }
    }
}
