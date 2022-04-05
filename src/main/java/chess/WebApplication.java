package chess;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApplication {

    ChessController chessController = new ChessController();


    public static void main(String[] args) {
        staticFiles.location("/static");
        port(8080);

        ChessController chessController = new ChessController();
        chessController.run();
    }

}
