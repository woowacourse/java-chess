package chess.web.controller;

import chess.board.Board;
import chess.web.service.ChessService;
import chess.web.service.dto.BoardDto;
import chess.web.service.dto.MoveDto;
import chess.web.service.dto.ScoreDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private final ChessService chessService;
    private static final Gson gson = new Gson();

    public ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/api/load", (req, res) -> {
            Board board = chessService.loadGame(1L);
            return gson.toJson(BoardDto.from(board));
        });

        get("/api/restart", (req, res) -> {
            Board board = chessService.initBoard(1L);
            return gson.toJson(BoardDto.from(board));
        });

        get("/api/status", (req, res) -> {
            ScoreDto status = chessService.getStatus(1L);
            return gson.toJson(status);
        });

        post("/api/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            Board board = chessService.move(moveDto, 1L);

            return gson.toJson(BoardDto.from(board));
        });
    }
}
