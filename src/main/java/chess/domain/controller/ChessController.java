package chess.domain.controller;

import static spark.Spark.get;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        final Gson gson = new Gson();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/load", (req, res) -> {
            return gson.toJson(new ChessGameDto(chessGame, "success", ""));
        });

        get("/start", (req, res) -> {
            return gson.toJson(start(chessGame));
        });

        get("/end", (req, res) -> {
            return gson.toJson(end(chessGame));
        });
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static ChessGameDto start(final ChessGame chessGame) {
        try {
            chessGame.start();
            return new ChessGameDto(chessGame, "success", "");
        } catch (final Exception e) {
            return new ChessGameDto(chessGame, "error", e.getMessage());
        }
    }

    private static ChessGameDto end(final ChessGame chessGame) {
        try {
            chessGame.end();
            return new ChessGameDto(chessGame, "success", "");
        } catch (final Exception e) {
            return new ChessGameDto(chessGame, "error", e.getMessage());
        }
    }
}
