package chess;

import chess.controller.chessround.ChessRoundController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "/");
        });

        get(ChessRoundController.PATH_CHESS_ROUND, ChessRoundController.fetchChessRound);
        post(ChessRoundController.PATH_CHESS_ROUND, ChessRoundController.handleChessMove);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
