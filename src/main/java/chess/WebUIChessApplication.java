package chess;

import chess.controller.WebController;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("public");

        WebController webController = new WebController();

        get("/", webController::mainPage);
        post("/start", webController::startGame);
        get("/create/:room", webController::createRoom);
        post("/move", webController::move);
        post("/movable", webController::movablePosition);
        post("/score", webController::score);
        get("/clear/:room", webController::clear);
    }
}
