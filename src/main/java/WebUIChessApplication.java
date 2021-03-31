import chess.controller.web.WebController;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("static");

        WebController.start();
    }

}
