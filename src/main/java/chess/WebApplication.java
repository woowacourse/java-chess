package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.GameController;
import chess.dto.BoardDto;
import chess.game.Game;
import chess.status.Ready;
import chess.status.State;
import chess.view.Command;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        final Game game = new Game(ready());
        final GameController controller = new GameController();

        get("/", (req, res) -> {
            final BoardDto boardDto = BoardDto.toDto(game.getBoard());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto.getValue());
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final Request request = Request.of(req.body());
            final String command = request.command();
            controller.move(game, command);
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static State ready() {
        try {
            return Ready.start(Command.of("start"));
        } catch (final IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return ready();
        }
    }
}
