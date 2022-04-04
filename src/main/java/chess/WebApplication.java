package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.dao.PieceDao;
import chess.dto.response.PieceResult;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        final PieceDao pieceDao = new PieceDao();

        port(8083);
        staticFileLocation("/static");
        final ChessController controller = new ChessController();
        get("/", ((request, response) -> controller.home()), new HandlebarsTemplateEngine());
        post("/start", controller::start, new HandlebarsTemplateEngine());
        get("/game/:boardId", controller::game, new HandlebarsTemplateEngine());
        post("/move/:boardId", "application/json", controller::move);
        get("/score", controller.score(), new JsonTransformer());
        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(final Map<String, PieceResult> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
