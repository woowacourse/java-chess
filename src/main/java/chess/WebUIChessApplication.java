package chess;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.patch;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.controller.PieceController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final HandlebarsTemplateEngine TEMPLATE_ENGINE = new HandlebarsTemplateEngine();

    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessController chessController = new ChessController();
        PieceController pieceController = new PieceController();

        get("/", (req, res) -> render(new HashMap<>(), "main.html"));

        path("/chess", () -> {
            path("", () -> {
                get("", (req, res) -> render(new HashMap<>(), "chess.html"));
                get("/ids", chessController::getIds);
                get("/:chessId/turn", chessController::getTurn);
                post("", chessController::insert);
                patch("/:chessId/turn", chessController::updateTurn);
                delete("/:chessId", chessController::delete);
            });

            path("/:chessId/pieces", () -> {
                get("", pieceController::get);
                post("", pieceController::insert);
                patch("/move", pieceController::move);
                delete("", pieceController::delete);
            });
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
