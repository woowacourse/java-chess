package chess;


import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Command command = InputView.inputStart();
        if (command.isNotStart()) {
            System.exit(0);
        }

        Board board = ChessBoard.initiaize();
        OutputView.printInitializedBoard(board);
    }
}
