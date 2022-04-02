package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.dao.MemoryTurnDaoImpl;
import chess.web.controller.ChessWebController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessWebController chessWebController = new ChessWebController(new MemoryTurnDaoImpl());

        get("/", chessWebController::index, HANDLEBARS_TEMPLATE_ENGINE);
        get("/start", chessWebController::create, HANDLEBARS_TEMPLATE_ENGINE);
    }
}
