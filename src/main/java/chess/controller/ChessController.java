package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;
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

    public void run() {
        final ChessService chessService = new ChessService();
        final Gson gson = new Gson();
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/load", (req, res) -> {
            chessService.load(chessGame);
            return gson.toJson(new ChessResponseDto(chessGame));
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

    private ChessResponseDto start(final ChessGame chessGame) {
        try {
            initializeChessGame(chessGame);
            chessGame.start();
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }

    private void initializeChessGame(final ChessGame chessGame) {
        if (!chessGame.isNotEnded()) {
            chessGame.initialize();
        }
    }

    private ChessResponseDto end(final ChessGame chessGame) {
        try {
            chessGame.end();
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
            chessGame.move(Position.create(moveDto.getSource()), Position.create(moveDto.getTarget()));
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }
}
