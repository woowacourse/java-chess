package chess;

import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
