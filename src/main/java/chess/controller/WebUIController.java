package chess.controller;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIController {

    private static GameStatus gameStatus = new NothingHappened();

    public static void run() {
        try {
            runWithoutException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runWithoutException() {
        staticFiles.location("/static");

        gameStatus = gameStatus.start();

        get("/", WebUIController::renderInitialUI);
    }

    private static String renderInitialUI(Request request, Response response) {

        Map<Position, Piece> board = gameStatus.getBoard();
        Map<String, Object> model = new HashMap<>();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            model.put(entry.getKey().toString(), entry.getValue());
        }
        model.put("empty-image", "image/peace/empty.png");

        return render(model, "chess.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
