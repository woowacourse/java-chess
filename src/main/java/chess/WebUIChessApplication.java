package chess;

import chess.controller.WebChessGame;
import chess.exception.InvalidMovementException;
import chess.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.PreparedStatement;
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
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            System.out.println(String.format("받은 source:%s, target:%s", source,target));
            try {
                game.play(source, target);
            } catch (InvalidMovementException e) {
                System.out.println(e.getMessage());
                //TODO: 브라우저 위에 alert로 뜨게하고 싶음.
                res.redirect("/error");
            }
            Map<String, Object> model = new HashMap<>();
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

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "error.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}