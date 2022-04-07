package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.piece.PieceColor;
import chess.dto.MovableDto;
import chess.dto.ResponseDto;
import chess.dto.ResponseStatusDto;
import chess.domain.game.ChessGame;
import chess.domain.game.Status;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebGameController {

    private final ChessService chessService;

    public WebGameController() {
        this.chessService = new ChessService();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        Gson gson = new Gson();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });
        get("/load", (req, res) -> {
            return gson.toJson(load());
        });
        get("/start", (req, res) -> {
            return gson.toJson(start());
        });
        post("/move", (req, res) -> {
            final MovableDto movableDto = gson.fromJson(req.body(), MovableDto.class);
            return gson.toJson(move(movableDto));
        });
        get("/status", (req, res) -> {
            return gson.toJson(status());
        });
        get("/end", (req, res) -> {
           return gson.toJson(end());
        });
    }

    private ResponseDto load() {
        ChessGame chessGame = null;
        try {
            chessGame = chessService.load();
            return ResponseDto.createResponseDto(chessGame.turn().name(), chessGame);
        } catch (IllegalArgumentException e) {
            return ResponseDto.createErrorResponseDto(e.getMessage(), chessGame);
        }
    }

    private ResponseDto start() {
        ChessGame chessGame = null;
        try {
            chessGame = chessService.createRoom();
            return ResponseDto.createResponseDto(chessGame.turn().name(), chessGame);
        } catch (IllegalArgumentException e) {
            return ResponseDto.createErrorResponseDto(e.getMessage(), chessGame);
        }
    }

    private ResponseDto move(MovableDto movableDto) {
        ChessGame chessGame = null;
        try {
            chessGame = chessService.load();
            chessService.move(chessGame, movableDto.getSource(), movableDto.getTarget());
            return ResponseDto.createResponseDto(chessGame.turn().name(), chessGame);
        } catch (RuntimeException e) {
            return ResponseDto.createErrorResponseDto(e.getMessage(), chessGame);
        }
    }

    private ResponseStatusDto status() {
        try {
            ChessGame chessGame = chessService.load();
            final Status status = chessService.status(chessGame);
            return ResponseStatusDto.createResponseStatusDto(status);
        } catch (IllegalArgumentException e) {
            return ResponseStatusDto.createErrorResponseDto(e.getMessage());
        }
    }

    private ResponseDto end() {
        ChessGame chessGame = null;
        try {
            chessGame = chessService.load();
            chessGame = chessService.end(chessGame);
            return new ResponseDto(PieceColor.NONE.getName(), chessGame);
        } catch (final Exception e) {
            return ResponseDto.createErrorResponseDto(e.getMessage(), chessGame);
        }
    }
}
