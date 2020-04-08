package chess.controller;

import chess.dao.ChessBoardDaoImpl;
import chess.domain.piece.position.Position;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();
    private static final ChessService chessService = new ChessService(new ChessBoardDaoImpl());

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/init", (req, res) -> gson.toJson(chessService.initChessBoard()));

        get("/continue", (req, res) -> gson.toJson(chessService.board()));

        post("/move", (req, res) -> {
            String source = req.queryParams("sourcePosition");
            String target = req.queryParams("targetPosition");
            Position sourcePosition = Position.of(source);
            Position targetPosition = Position.of(target);

            return gson.toJson(chessService.move(sourcePosition, targetPosition));
        });
    }
}
