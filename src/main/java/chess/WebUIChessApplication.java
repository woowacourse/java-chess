package chess;

import java.util.Collections;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.patch;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final HandlebarsTemplateEngine TEMPLATE_ENGINE = new HandlebarsTemplateEngine();

    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessController chessController = new ChessController();

        get("/", (req, res) -> render(Collections.emptyMap(), "main.html"));

        path("/chess", () -> {
            get("/:chessId/view", (req, res) -> render(Collections.emptyMap(), "chess.html"));
            get("/:chessId", chessController::get);
            post("", chessController::insert);
            patch("/:chessId", chessController::move);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
