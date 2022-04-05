package chess.controller;

import static spark.Spark.get;

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

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
