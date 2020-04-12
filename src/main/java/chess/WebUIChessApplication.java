package chess;

import chess.webController.*;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("templates");

        ChessMainPageUrlController.run();
        ChessFirstStartUrlController.run();
        ChessContinueStartUrlController.run();
        ChessMoveUrlController.run();
        ChessStatusUrlController.run();
    }
}
