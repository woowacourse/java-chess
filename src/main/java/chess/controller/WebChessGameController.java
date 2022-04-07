package chess.controller;

import chess.domain.ChessWebGame;
import chess.dto.ErrorMessageDto;
import chess.dto.MoveDto;
import chess.dto.ResultDto;
import chess.dto.ScoreDto;
import chess.service.ChessService;
import chess.view.ChessMap;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGameController {

    private static final Gson gson = new Gson();

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        final ChessWebGame chessWebGame = new ChessWebGame();
        final ChessService chessService = new ChessService();

        get("/", (req, res) ->
                render(new HashMap<>(), "index.html")
        );

        get("/start", (req, res) -> {
            final ChessMap chessMap = chessService.initializeGame(chessWebGame);
            return gson.toJson(chessMap);
        });

        get("/load", (req, res) -> {
            ChessMap chessMap = chessService.load(chessWebGame);
            return gson.toJson(chessMap);
        });

        get("/status", (req, res) -> {
            final ScoreDto scoreDto = chessService.getStatus(chessWebGame);
            return gson.toJson(scoreDto);
        });

        get("/end", (req, res) -> {
            final ResultDto resultDto = chessService.getResult(chessWebGame);
            chessService.initializeGame(chessWebGame);
            return gson.toJson(resultDto);
        });

        post("/move", (req, res) -> {
            final MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            try {
                final ChessMap chessMap = chessService.move(chessWebGame, moveDto);
                return gson.toJson(chessMap);
            } catch (final Exception e) {
                return gson.toJson(new ErrorMessageDto(e.getMessage()));
            }
        });
    }
}
