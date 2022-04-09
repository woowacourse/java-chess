package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.dto.ChessResponseDto;
import chess.dto.ErrorResponseDto;
import chess.dto.GameDto;
import chess.dto.MoveRequestDto;
import chess.dto.RequestDto;
import chess.dto.StatusResponseDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
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
        get("/", (req, res) -> render(new HashMap<>(), "lobby.html"));

        get("/load/games", (req, res) -> GSON.toJson(loadGames()));

        get("/game", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            model.put("id", req.queryParams("id"));
            return render(model, "game.html");
        });

        post("/load", (req, res) -> {
            final RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return GSON.toJson(load(requestDto.getId()));
        });

        post("/start", (req, res) -> {
            final RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return GSON.toJson(start(requestDto.getId()));
        });

        post("/end", (req, res) -> {
            final RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return GSON.toJson(end(requestDto.getId()));
        });

        post("/status", (req, res) -> {
            final RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return GSON.toJson(status(requestDto.getId()));
        });

        post("/move", (req, res) -> {
            final MoveRequestDto moveDto = GSON.fromJson(req.body(), MoveRequestDto.class);
            return GSON.toJson(move(moveDto.getId(), moveDto));
        });

        exception(Exception.class, (e, req, res) -> res.body(GSON.toJson(new ErrorResponseDto(e.getMessage()))));
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private List<GameDto> loadGames() {
        return chessService.loadGames();
    }

    private ChessResponseDto load(final String id) {
        final ChessGame chessGame = chessService.load(id);
        return chessService.createChessResponseDto(chessGame);
    }

    private ChessResponseDto start(final String id) {
        ChessGame chessGame = chessService.load(id);
        chessGame = chessService.start(id, chessGame);
        return chessService.createChessResponseDto(chessGame);
    }

    private ChessResponseDto end(final String id) {
        ChessGame chessGame = chessService.load(id);
        chessGame = chessService.end(id, chessGame);
        return chessService.createChessResponseDto(chessGame);
    }

    private StatusResponseDto status(final String id) {
        ChessGame chessGame = chessService.load(id);
        chessGame = chessService.status(chessGame);
        return chessService.createStatusResponseDto(chessGame);
    }

    private ChessResponseDto move(final String id, final MoveRequestDto moveDto) {
        ChessGame chessGame = chessService.load(id);
        chessGame = chessService.move(id, chessGame, moveDto.getSource(), moveDto.getTarget());
        return chessService.createChessResponseDto(chessGame);
    }
}
