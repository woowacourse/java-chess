package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.ChessGame;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        ready();
        start(chessGame);
        move(chessGame);
    }

    private void ready() {
        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    public void start(final ChessGame chessGame) {
        get("/start", ((req, res) -> {
            if (chessGame.isPlaying()) {
                throw new IllegalStateException("이미 게임이 시작중입니다.");
            }
            chessGame.start();
            return render(chessGame.toMap(), "index.html");
        }));
    }

    private void move(final ChessGame chessGame) {
        post("/move", ((req, res) -> {
            checkGameIsNotPlaying(chessGame);
            chessGame.move(
                    req.queryParams("source"), req.queryParams("target"));
            return render(chessGame.toMap(), "index.html");
        }));
    }

    private void checkGameIsNotPlaying(final ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
