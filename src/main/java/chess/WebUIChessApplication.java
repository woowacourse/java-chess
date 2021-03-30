package chess;

import chess.domain.Board;
import chess.domain.ChessGame;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8084);
        final Gson GSON = new Gson();
        ChessService chessService = new ChessService();

        JsonTransformer jsonTransformer = new JsonTransformer();
        staticFiles.location("/public");
        ChessGame chessGame = new ChessGame(Board.getGamingBoard());
        chessGame.start();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        post("/move", (req, res) -> {
            RequestPosition requestPosition = GSON.fromJson(req.body(), RequestPosition.class);
            return chessService.move(requestPosition, chessGame);
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}