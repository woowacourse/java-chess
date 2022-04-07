package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.dto.ChessResponseDto;
import chess.dto.ErrorResponseDto;
import chess.dto.MoveRequestDto;
import chess.dto.StatusResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private static final Gson GSON = new Gson();

    final ChessService chessService;

    public ChessWebController() {
        chessService = new ChessService();
    }

    public void run() {
        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/load", (req, res) -> {
            return GSON.toJson(load());
        });

        get("/start", (req, res) -> {
            return GSON.toJson(start());
        });

        get("/end", (req, res) -> {
            return GSON.toJson(end());
        });

        get("/status", (req, res) -> {
            return GSON.toJson(status());
        });

        post("/move", (req, res) -> {
            final MoveRequestDto moveDto = GSON.fromJson(req.body(), MoveRequestDto.class);
            return GSON.toJson(move(moveDto));
        });

        exception(Exception.class, (e, req, res) -> {
            res.body(GSON.toJson(new ErrorResponseDto(e.getMessage())));
        });
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private ChessResponseDto load() {
        final ChessGame chessGame = chessService.load();
        return chessService.createChessResponseDto(chessGame);
    }

    private ChessResponseDto start() {
        ChessGame chessGame = chessService.load();
        chessGame = chessService.start(chessGame);
        return chessService.createChessResponseDto(chessGame);
    }

    private ChessResponseDto end() {
        ChessGame chessGame = chessService.load();
        chessGame = chessService.end(chessGame);
        return chessService.createChessResponseDto(chessGame);
    }

    private StatusResponseDto status() {
        ChessGame chessGame = chessService.load();
        chessGame = chessService.status(chessGame);
        return chessService.createStatusResponseDto(chessGame);
    }

    private ChessResponseDto move(final MoveRequestDto moveDto) {
        ChessGame chessGame = chessService.load();
        chessGame = chessService.move(chessGame, moveDto.getSource(), moveDto.getTarget());
        return chessService.createChessResponseDto(chessGame);
    }
}
