package chess;

import chess.DTO.PieceDTO;
import chess.controller.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIApplication {
    public static void main(String[] args) {
        staticFileLocation("/templates");

        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            List<PieceDTO> pieces = chessGame.run(req.queryParams("command"));
            Map<String, Object> model = new LinkedHashMap<>();
            model.put("pieces", pieces);
            return render(model, "index.hbs");
        });

        post("/", (req, res) -> {
            Map<String, Object> model = new LinkedHashMap<>();
            List<PieceDTO> pieces;
            try {
                pieces = chessGame.run(req.queryParams("command"));
            } catch (RuntimeException e) {
                pieces = chessGame.stay();
                model.put("error", e.getMessage());
            }
            model.put("pieces", pieces);
            return render(model, "index.hbs");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
