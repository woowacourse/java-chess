package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.service.ChessGameService;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        final ChessGameService chessGameService = new ChessGameService();
        final Gson gson = new Gson();

        staticFiles.location("/static");

        get("/", (req, res) -> {
            chessGameService.initializeGame();
            return render(Collections.emptyMap(), "chess.html");
        });

        get("/game", (req, res) -> {
            final String source = req.queryParams("source");
            final String target = req.queryParams("target");
            return gson.toJson(Collections.singletonMap("isMovable",
                chessGameService.checkMovement(source, target)));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
