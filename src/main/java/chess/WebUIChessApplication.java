package chess;

import chess.controller.ChessGameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
        ChessGameController chessGameController = new ChessGameController();
        chessGameController.run();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
