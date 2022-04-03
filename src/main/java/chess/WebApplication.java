package chess;

import chess.domain.game.BoardInitializer;
import chess.domain.game.ChessController;
import chess.domain.game.Game;
import chess.view.ResponseDto;
import chess.view.BoardDto;
import chess.view.StatusDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("/static");

        ChessController controller = new ChessController();
        Game game = new Game(new BoardInitializer());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", BoardDto.of(game.toMap()));
            return render(model, "index.html");
        });
        post("/move", (req, res) -> {
            final String[] split = req.body().strip().split("=");
            ResponseDto response = controller.move(game, split[1]);
            return response.toString();
        });
        get("/start", (req, res) -> {
            game.restart(new BoardInitializer());
            res.redirect("/");
            return null;
        });

        post("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result", controller.status(game));
            game.restart(new BoardInitializer());
            return render(model, "result.html");
        });

        get("/status", (req, res) -> {
            final StatusDto statusDto = controller.status(game);
            return statusDto.toString();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
