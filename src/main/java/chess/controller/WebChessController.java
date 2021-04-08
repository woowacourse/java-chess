package chess.controller;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;
import chess.dto.requestDto.MoveRequestDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final ChessService chessService;
    private final Gson gson;

    public WebChessController() {
        this.chessService = new ChessService();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

        get("/data", (req, res) -> {
            int chessGameId = Integer.parseInt(req.queryParams("id"));
            ChessGame chessGame = chessService.findChessGameById(chessGameId);
            return getChessGameGson(chessGame);
        });

        post("/move", (req, res) -> {
            try {
                int chessGameId = Integer.parseInt(req.queryParams("id"));
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                String source = moveRequestDto.getSource();
                String target = moveRequestDto.getTarget();
                ChessGame chessGame = chessService.moveFromSourceToTarget(chessGameId, source, target);
                return getChessGameGson(chessGame);
            } catch (Exception e) {
                res.status(400);
                return e.getMessage();
            }
        });

        post("/generate", (req, res) -> {
            chessService.generateChessGame();
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chess-game-list", (req, res) -> {
            List<Integer> chessGameList = chessService.getAllChessGameId();
            String chessGameListJson = gson.toJson(chessGameList);
            return gson.toJson(chessGameListJson);
        });

        get("/reset", (req, res) -> {
            int chessGameId = Integer.parseInt(req.queryParams("id"));
            chessService.resetChessGame(chessGameId);
            ChessGame chessGame = chessService.findChessGameById(chessGameId);
            return getChessGameGson(chessGame);
        });
    }

    private String getChessGameGson(ChessGame chessGame) {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame);
        String chessGameDtoJson = gson.toJson(chessGameDto);
        return gson.toJson(chessGameDtoJson);
    }
}
