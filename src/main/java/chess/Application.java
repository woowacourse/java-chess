package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Board board = BoardFactory.createBoard();
        OutputView.printInitializedBoard(board);
    }
}
