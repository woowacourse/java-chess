package chess;

import java.util.Collections;
import java.util.Map;

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

        get("/", (req, res) -> render(Collections.emptyMap(), "main.html"));

        path("/chess", () -> {
            path("", () -> {
                get("", (req, res) -> render(Collections.emptyMap(), "chess.html"));
                post("", chessController::insert);
                get("/ids", chessController::getIds);
                get("/:chessId/turn", chessController::getTurn);
                patch("/:chessId/turn", chessController::updateTurn);
                delete("/:chessId", chessController::delete);
            });

            path("/:chessId/pieces", () -> {
                get("", pieceController::get);
                post("", pieceController::insert);
                delete("", pieceController::delete);
                get("/score", pieceController::getScore);
                patch("/move", pieceController::move);
            });
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
