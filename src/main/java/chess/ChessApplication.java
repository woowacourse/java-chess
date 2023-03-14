package chess;

import chess.domain.board.ChessBoard;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        OutputView.showBoard(ChessBoard.create().pieces());
    }
}
