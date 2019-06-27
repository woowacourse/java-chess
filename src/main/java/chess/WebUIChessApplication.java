package chess;

import chess.controller.ChessGameController;
import chess.controller.ErrorController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        port(8080);

        ChessGameController chessGameController = new ChessGameController();
        ErrorController errorController = new ErrorController();

        get("/", chessGameController::showIndex);

        get("/game", chessGameController::game);

        get("/continue/:gameId", chessGameController::continueGame);

        get("/move", chessGameController::move);

        get("/error", errorController::print);

        exception(Exception.class, errorController::catchException);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
