package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.service.ChessGameService;
import java.util.Collections;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        final ChessGameService chessGameService = new ChessGameService();

        staticFiles.location("/static");

        get("/", (req, res) -> {
            chessGameService.initializeGame();
            return render(Collections.emptyMap(), "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
