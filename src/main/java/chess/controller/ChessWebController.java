package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.MoveDto;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {
    private final ChessGameService chessGameService;

    public ChessWebController() {
        this.chessGameService = new ChessGameService();
    }

    public void run() {
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> gson.toJson(chessGameService.start().getBoard()));

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            return gson.toJson(chessGameService.move(res, moveDto).getBoard());
        });

        get("/turn", (req, res) -> gson.toJson(chessGameService.turn().getTurn()));

        get("/status", (req, res) -> gson.toJson(chessGameService.status().getScore()));

        get("/end", (req, res) -> gson.toJson(chessGameService.end()));
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
