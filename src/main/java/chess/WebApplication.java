package chess;

import chess.controller.ChessController;
import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.generator.BoardGenerator;

public class WebApplication {
    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BasicBoardGenerator();
        ChessController chessController = new ChessController();
        chessController.run(boardGenerator);
    }
}
