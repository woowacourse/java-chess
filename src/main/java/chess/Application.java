package chess;


import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Board board = ChessBoard.initiaize();
        OutputView.printInitializedBoard(board);
    }
}
