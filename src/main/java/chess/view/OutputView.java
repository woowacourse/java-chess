package chess.view;

import chess.domain.Board;
import chess.util.RenderingUtils;

public class OutputView {

    public void printBoard(Board board) {
        System.out.println(RenderingUtils.renderBoard(board));
    }
}
