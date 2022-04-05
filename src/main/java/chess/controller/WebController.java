package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

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
        status(chessGame);
        end(chessGame);
    }

    private void ready() {
        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void start(final ChessGame chessGame) {
        get("/start", ((req, res) -> {
            chessGame.start();
            return render(chessGame.toMap(), "index.html");
        }));
    }

    private void move(final ChessGame chessGame) {
        post("/move", ((req, res) -> {
            chessGame.move(
                    req.queryParams("source"), req.queryParams("target"));
            return render(chessGame.toMap(), "index.html");
        }));
    }

    private void status(final ChessGame chessGame) {
        get("/status", ((req, res) -> {
            final JsonTransformer jsonTransformer = new JsonTransformer();
            return jsonTransformer.render(chessGame.getScore());
        }));
    }

    private void end(final ChessGame chessGame) {
        get("/end", ((req, res) -> {
            final Map<String, Object> model = chessGame.toMap();
            chessGame.end();
            if (!chessGame.isPlaying()) {
                throw new IllegalStateException();
            }
            return render(model, "index.html");
        }));
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
