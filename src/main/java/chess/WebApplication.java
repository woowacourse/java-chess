package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.dto.MovePositionCommandDto;
import chess.web.util.JsonTransformer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {


    public static void main(String[] args) {
        port(8082);

        staticFiles.location("/static");

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        AtomicReference<ChessGame> game = new AtomicReference<>(ChessGame.of());
        AtomicReference<String> gameId = new AtomicReference<>("");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            gameId.set(req.queryParams("game"));
            game.set(ChessGame.createOrGet(String.valueOf(gameId)));

            Map<String, Object> model = game.get().toBoard(String.valueOf(gameId)).getBoardMap();

            return render(model, "game.html");
        });

        get("/game/progress", (req, res) -> {
            Map<String, Object> model = game.get().toBoard(String.valueOf(gameId)).getBoardMap();

            return render(model, "game.html");
        });

        post("/game/move", (req, res) -> {
            JSONObject jObject = new JSONObject(req.body());

            String from = jObject.getString("from");
            String to = jObject.getString("to");

            game.get().moveChessmen(new MovePositionCommandDto(from, to));

            res.redirect("/game/progress");

            return null;
        });

        get("/game/start", (req, res) -> {
            game.get().clean(String.valueOf(gameId));

            game.set(ChessGame.of(chessmenInitializer.init(), String.valueOf(gameId)));

            res.redirect("/game/progress");

            return null;
        });

        get("/game/end", (req, res) -> {
            game.get().forceEnd(String.valueOf(gameId));

            res.redirect("/game/progress");

            return null;
        });

        get("/game/status", (req, res) -> {
            res.type("application/json");
            JsonTransformer jsonTransformer = new JsonTransformer();

            Map<String, Object> model = game.get().calculateGameResult().getGameResultMap();

            return jsonTransformer.render(model);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
