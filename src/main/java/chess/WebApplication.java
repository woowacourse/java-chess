package chess;

import chess.controller.WebController;

import static spark.Spark.*;


public class WebApplication {
    private static final WebController webController = new WebController();

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", webController::boardPage);
        post("/move", webController::move);
        post("/start", webController::start);

        exception(Exception.class, webController::boardPageWithException);
    }
}
