package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.ChessResponseDto;
import chess.dto.MoveRequestDto;
import chess.dto.StatusResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private static final Gson GSON = new Gson();

    final ChessService chessService;

    public ChessController() {
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
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private ChessResponseDto load() {
        try {
            chessService.load();
            return chessService.createChessResponseDto();
        } catch (final Exception e) {
            return chessService.createErrorChessResponseDto(e.getMessage());
        }
    }

    private ChessResponseDto start() {
        try {
            chessService.start();
            return chessService.createChessResponseDto();
        } catch (final Exception e) {
            return chessService.createErrorChessResponseDto(e.getMessage());
        }
    }

    private ChessResponseDto end() {
        try {
            chessService.end();
            return chessService.createChessResponseDto();
        } catch (final Exception e) {
            return chessService.createErrorChessResponseDto(e.getMessage());
        }
    }

    private StatusResponseDto status() {
        try {
            chessService.status();
            return chessService.createStatusResponseDto();
        } catch (final Exception e) {
            return chessService.creatErrorStatusResponseDto(e.getMessage());
        }
    }

    private ChessResponseDto move(final MoveRequestDto moveDto) {
        try {
            chessService.move(moveDto.getSource(), moveDto.getTarget());
            return chessService.createChessResponseDto();
        } catch (final Exception e) {
            return chessService.createErrorChessResponseDto(e.getMessage());
        }
    }
}
