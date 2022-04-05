package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessController;
import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.generator.BoardGenerator;
import chess.utils.DatabaseUtil;
import java.sql.Connection;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        BoardGenerator boardGenerator = new BasicBoardGenerator();
        Connection connection = DatabaseUtil.getConnection();
        ChessController chessController = new ChessController(connection);
        chessController.run(boardGenerator);
    }
}
