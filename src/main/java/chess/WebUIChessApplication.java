package chess;

import chess.controller.WebChessGame;
import chess.exception.InvalidMovementException;
import chess.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        staticFiles.location("/public");
        WebChessGame game = new WebChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", WebOutputView.printBoard(game.getBoard()));
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            try {
                game.play(source, target);
            } catch (InvalidMovementException e) {
                model.put("message", e.getMessage());
                return render(model, "error.html");
            }
            if (game.isFinished()) {
                model.put("winner", game.isTurnWhite() ? "흑팀" : "백팀");
                return render(model, "result.html");
            }
            model.put("board", WebOutputView.printBoard(game.getBoard()));
            return render(model, "index.html");
        });

        post("/newgame", (req, res) -> {
            game.init();
            res.redirect("/");
            return null;
        });

        post("/scores", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("scores", Scores.calculateScores(game.getBoard()));
            return render(model, "scores.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}