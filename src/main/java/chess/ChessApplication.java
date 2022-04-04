package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.ChessController;
import chess.dto.ResponseDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessApplication {

    private static final ChessController chessController = new ChessController();

    public static void main(String[] args) {

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessController.getBoard());
            return render(model, "chess-game.html");
        });

        post("/move", (req, res) -> {
            final String[] split = req.body().strip().split("=")[1].split(" ");
            ResponseDto response = chessController.move(split[1], split[2]);
            return response.toString();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
