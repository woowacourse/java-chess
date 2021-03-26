package chess;

import chess.service.ChessService;
import chess.web.dto.MoveRequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");
        ChessService chessService = new ChessService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, String> reqBody = (Map) GSON.fromJson(req.body(), new HashMap<>().getClass());
            String sourcePosition = reqBody.get("source");
            String targetPosition = reqBody.get("target");
            return chessService.move(new MoveRequestDto(sourcePosition, targetPosition));
        }, new JsonTransformer());

        post("/start", (req, res) -> {
            try {
                return chessService.start();
            } catch (Exception e) {
                return e;
            }
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
