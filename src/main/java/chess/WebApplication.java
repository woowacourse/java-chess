package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessController;
import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.generator.BoardGenerator;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        BoardGenerator boardGenerator = new BasicBoardGenerator();
        ChessController chessController = new ChessController();
        chessController.run(boardGenerator);
    }
}
