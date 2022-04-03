package chess;

import static spark.Spark.*;

import chess.dto.CommandRequest;
import chess.dto.response.BoardResult;
import chess.dto.response.PieceResult;
import chess.game.Command;
import chess.game.Game;
import chess.status.Ready;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");

        final Game game = new Game(Ready.start(Command.START));

        get("/", (req, res) -> render(new BoardResult(game.getBoard().getValue()).getValue(), "index.html"));

        post("/move", (req, res) -> {
            final String body = req.body();
            final String[] split = body.split("&");
            final String s = split[0];
            final String s1 = split[1];

            game.run(new CommandRequest("move", s.split("=")[1], s1.split("=")[1]));
            res.redirect("/");
            return null;
        });
    }

    private static String render(final Map<String, PieceResult> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
