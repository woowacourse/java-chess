package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.service.ChessGameService;
import chess.web.util.JsonTransformer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

    public static void main(String[] args) {

        AtomicReference<ChessGameService> chessGameService = new AtomicReference<>();

        port(8082);

        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            chessGameService.set(new ChessGameService(req.queryParams("gameId")));

            Map<String, Object> model = chessGameService.get()
                .createOrGet()
                .getBoardMap();

            return render(model, "game.html");
        });

        get("/game/progress", (req, res) -> {
            Map<String, Object> model = chessGameService.get()
                .getCurrentGame()
                .getBoardMap();

            return render(model, "game.html");
        });

        get("/game/status", (req, res) -> {
            res.type("application/json");
            JsonTransformer jsonTransformer = new JsonTransformer();

            Map<String, Object> model = chessGameService.get()
                .calculateGameResult()
                .getGameResultMap();

            return jsonTransformer.render(model);
        });

        post("/game/start", (req, res) -> {
            chessGameService.get()
                .cleanGame();

            chessGameService.get()
                .initGame();

            res.redirect("/game/progress");
            return true;
        });

        post("/game/move", (req, res) -> {
            JSONObject jObject = new JSONObject(req.body());
            String from = jObject.getString("from");
            String to = jObject.getString("to");

            chessGameService.get()
                .move(from, to);

            res.redirect("/game/progress");
            return true;
        });

        post("/game/end", (req, res) -> {
            chessGameService.get()
                .forceEnd();

            res.redirect("/game/progress");
            return true;
        });

        post("/game/exit", (req, res) -> {
            chessGameService.get()
                .cleanGame();

            res.redirect("/");
            return true;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

}
