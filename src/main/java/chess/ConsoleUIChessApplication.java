package chess;

import chess.board.BoardGenerator;
import chess.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        ChessBoard chessBoard = new BoardGenerator().create();
        OutputView.showAllCommand();
        String command = InputView.inputCommand();

        while (!"end".equals(command)) {
            if ("start".equals(command)) {
                OutputView.showChessBoard(chessBoard);
            }
            command = InputView.inputCommand();
        }
    }
}
