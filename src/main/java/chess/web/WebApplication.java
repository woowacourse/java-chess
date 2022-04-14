package chess.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.web.controller.WebChessController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> webChessController.indexModel(res));

        get("/move", webChessController::movePiece);

        get("/start", (req, res) -> webChessController.startChess(res));

        get("/winner", (req, res) -> webChessController.getWinnerModel());

        exception(Exception.class,
                (exception, request, response) -> response.body(webChessController.getExceptionModel(exception)));
    }
}
