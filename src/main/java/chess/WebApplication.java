package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.web.controller.ChessWebController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessWebController chessWebController = new ChessWebController();

        get("/", chessWebController::index, HANDLEBARS_TEMPLATE_ENGINE);
        get("/start", chessWebController::create, HANDLEBARS_TEMPLATE_ENGINE);
        get("/move", chessWebController::move, HANDLEBARS_TEMPLATE_ENGINE);

        exception(Exception.class, chessWebController::exceptionHandle);
    }
}
