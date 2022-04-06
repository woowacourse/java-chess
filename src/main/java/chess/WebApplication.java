package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.board.Location;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();
        WebChessService webChessService = new WebChessService();

        get("/", (req, res) -> {
            return new ModelAndView(chessGame.toMap(), "index.html");
        }, new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            chessGame.start();
            webChessService.initializeGame(chessGame.getState());
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            chessGame.move(Location.of(source), Location.of(target));
            res.redirect("/");
            return null;
        });

        get("/status", (req, res) -> {
            return chessGame.status();
        }, new JsonTransformer());

        get("/end", (req, res) ->{
            chessGame.end();
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
