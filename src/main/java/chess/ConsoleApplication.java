package chess;

import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        OutputView.printStartMessage();
        startChess(chessBoard);
    }

    private static void startChess(ChessBoard chessBoard) {
        CommandRequest commandRequest = InputView.inputCommand();
        Command command = commandRequest.getCommand();

        if (command.isStart()) {
            chessBoard.init();
            OutputView.printChessBoard(chessBoard.getBoard());
            return;
        }

        startChess(chessBoard);
    }
}
