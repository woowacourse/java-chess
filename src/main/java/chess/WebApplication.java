package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.ChessGame;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            return new ModelAndView(chessGame.getBoard().toMap(), "index.html");
        }, new HandlebarsTemplateEngine());

        post("/move", (req, res) -> {
            Coordinate from = Coordinate.of(req.queryParams("from"));
            Coordinate to = Coordinate.of(req.queryParams("to"));
            chessGame.move(from, to);
            res.redirect("/");
            return null;
        });

        post("/start", (req, res) -> {
            chessGame.start();
            res.redirect("/");
            return null;
        });

        post("/end", (req, res) -> {
            chessGame.end();
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
