package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.JsonTransformer;
import chess.dao.BoardRepository;
import chess.dao.ChessGameRepository;
import chess.dto.GameDto;
import chess.dto.MoveDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private final ChessService chessService = new ChessService(new ChessGameRepository(), new BoardRepository());
    private final JsonTransformer jsonTransformer = new JsonTransformer();

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });
    }

    public void start() {
        post("/start", (req, res) -> chessService.start(), jsonTransformer);
    }

    public void runMain() {
        get("game/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    public void board() {
        post("/board", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.getBoard(gameDto.getGameId());
        }, jsonTransformer);
    }

    public void turn() {
        post("/turn", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.getTurn(gameDto.getGameId());
        }, jsonTransformer);
    }

    public void status() {
        post("/status", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            return chessService.status(gameDto.getGameId());
        }, jsonTransformer);
    }

    public void move() {
        post("/move", (req, res) -> {
            MoveDto moveDto = jsonTransformer.getGson().fromJson(req.body(), MoveDto.class);
            return chessService.move(moveDto);
        }, jsonTransformer);
    }

    public void end() {
        post("/end", (req, res) -> {
            GameDto gameDto = jsonTransformer.getGson().fromJson(req.body(), GameDto.class);
            chessService.end(gameDto.getGameId());
            return "chess game end!";
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
