package chess;

import chess.controller.ChessRoundController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<List<String>> pieces = new ArrayList<>();
            for (int r = 0; r < 8; r++) {
                List<String> tmp = new ArrayList<>();
                for (int c = 0; c < 8; c++) {
//                    tmp.add(Integer.toString(8 * r + c));
                    tmp.add("\u00A0");
                }
                pieces.add(tmp);
            }
            model.put("pieces", pieces);
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "index.hbs")
            );
            // return render(model, "index.html");

        });

        get(ChessRoundController.PATH_CHESS_ROUND, ChessRoundController.fetchChessRound);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
