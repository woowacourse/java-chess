package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.domain.result.Score;
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

    final ChessService chessService;
    final Gson gson;

    public ChessController() {
        chessService = new ChessService();
        gson = new Gson();
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/load", (req, res) -> {
            return gson.toJson(load(chessGame));
        });

        get("/start", (req, res) -> {
            return gson.toJson(start(chessGame));
        });

        get("/end", (req, res) -> {
            return gson.toJson(end(chessGame));
        });

        get("/status", (req, res) -> {
            return gson.toJson(status(chessGame));
        });

        post("/move", (req, res) -> {
            MoveRequestDto moveDto = gson.fromJson(req.body(), MoveRequestDto.class);
            return gson.toJson(move(chessGame, moveDto));
        });
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private ChessResponseDto load(final ChessGame chessGame) {
        try {
            chessService.load(chessGame);
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }

    private ChessResponseDto start(final ChessGame chessGame) {
        try {
            chessService.start(chessGame);
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }

    private ChessResponseDto end(final ChessGame chessGame) {
        try {
            chessService.end(chessGame);
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }

    private StatusResponseDto status(final ChessGame chessGame) {
        try {
            chessGame.status();
            final Score myScore = chessGame.calculateMyScore();
            final Score opponentScore = chessGame.calculateOpponentScore();
            return new StatusResponseDto(chessGame, myScore.getValue(), opponentScore.getValue(),
                    myScore.decideResult(opponentScore).getName());
        } catch (final Exception e) {
            return new StatusResponseDto("error", e.getMessage(), chessGame);
        }
    }

    private ChessResponseDto move(final ChessGame chessGame, final MoveRequestDto moveDto) {
        try {
            chessService.move(chessGame, moveDto);
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }
}
