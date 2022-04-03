package chess;

import static spark.Spark.get;

import chess.domain.ChessGame;
import chess.view.BoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "initial.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.start();
            model.put("board", BoardDto.of(chessGame.getBoard()));
            return render(model, "board.html");
        });


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
