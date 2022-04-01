package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.BoardViewDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Game game = new NewGame().init();
            BoardViewDto boardView = game.boardView();

            Map<String, Object> model = new HashMap<>();
            model.put("game_id", 1);
            model.put("board", boardView.webDisplay());
            return render(model, "game.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
