package chess;

import static spark.Spark.before;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.WebUIChessController;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        before(((request, response) -> {
            response.type("application/json");
        }));

        WebUIChessController webController = new WebUIChessController();
    }
}
