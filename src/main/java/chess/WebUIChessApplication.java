package chess;

import chess.domain.ChessGame;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("templates");
        Gson gson = new Gson();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame game = new ChessGame();
            Map<String, String> status = game.status();
            return render(model, "index.html");
        });

        get("/chess", (req, res) -> {
            ChessGame game = ChessGameService.findGameByGameId("1");
            return game.status();
        }, gson::toJson);

        post("/game/*/move", (req, res) -> ChessGameService.playGame(req), gson::toJson);

        post("/game/*", (req, res) -> {
            String gameId = req.splat()[0];
            ChessGame game = ChessGameService.findGameByGameId(gameId);
            req.session().attribute(gameId + "-game", game);
            return game.status();
        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
