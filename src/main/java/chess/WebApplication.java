package chess;

import chess.domain.game.BoardInitializer;
import chess.domain.game.ChessController;
import chess.domain.game.Game;
import chess.domain.game.ResponseDto;
import chess.view.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        port(8081);
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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
