package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.dto.CommandDto;
import chess.dto.MovePositionCommandDto;
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
        AtomicReference<ChessGame> game = new AtomicReference<>(ChessGame.of(chessmenInitializer.init()));

        get("/", (req, res) -> {
            Map<String, Object> model = game.get().toBoard().getBoardMap();

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String[] request = req.body().split("=");
            CommandDto commandDto = new CommandDto(request[1]);

            game.get().moveChessmen(new MovePositionCommandDto(commandDto.getFullCommand()));

            res.redirect("/");
            return null;
        });

        post("/ui/move", (req, res) -> {
            JSONObject jObject = new JSONObject(req.body());

            String from = jObject.getString("from");
            String to = jObject.getString("to");

            game.get().moveChessmen(new MovePositionCommandDto(from, to));

            res.redirect("/");
            return null;
        });

        get("/ui/start", (req, res) -> {

            game.set(ChessGame.of(chessmenInitializer.init()));

            if (game.get().isEnd()) {
                res.redirect("/");
            }

            res.redirect("/");

            return null;
        });

        get("/ui/end", (req, res) -> {

            game.set(ChessGame.of(chessmenInitializer.init()));

            res.redirect("/");

            return null;
        });

        get("ui/status", (req, res) -> {
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
