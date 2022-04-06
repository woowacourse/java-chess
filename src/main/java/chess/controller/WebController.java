package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        ready(chessGame);
        start(chessGame);
        move(chessGame);
        status(chessGame);
        end(chessGame);
    }

    private void ready(final ChessGame chessGame) {
        get("/", (req, res) -> {
            chessGame.initializeBoard();
            return render(chessGame.getAllPiecesByPosition());
        });
    }

    private void start(final ChessGame chessGame) {
        get("/start", ((req, res) -> {
            chessGame.start();
            return render(chessGame.getAllPiecesByPosition());
        }));
    }

    private void move(final ChessGame chessGame) {
        post("/move", ((req, res) -> {
            chessGame.move(
                    req.queryParams("source"), req.queryParams("target"));
            return render(chessGame.getAllPiecesByPosition());
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
            final Map<String, Object> model = chessGame.getAllPiecesByPosition();
            chessGame.end();
            return render(model);
        }));
    }

    private String render(final Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
