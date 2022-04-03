package chess.domain.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.domain.result.Score;
import chess.dto.ChessGameDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        final Gson gson = new Gson();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/load", (req, res) -> {
            return gson.toJson(new ChessGameDto(chessGame));
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
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            return gson.toJson(move(chessGame, moveDto));
        });
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static ChessGameDto start(final ChessGame chessGame) {
        try {
            chessGame.start();
            return new ChessGameDto(chessGame);
        } catch (final Exception e) {
            return new ChessGameDto("error", e.getMessage(), chessGame);
        }
    }

    private static ChessGameDto end(final ChessGame chessGame) {
        try {
            chessGame.end();
            return new ChessGameDto(chessGame);
        } catch (final Exception e) {
            return new ChessGameDto("error", e.getMessage(), chessGame);
        }
    }

    private static StatusDto status(final ChessGame chessGame) {
        try {
            chessGame.status();
            final Score myScore = chessGame.calculateMyScore();
            final Score opponentScore = chessGame.calculateOpponentScore();
            return new StatusDto(chessGame.getTurnName(), chessGame.getOppositeTurnName(), myScore.getValue(),
                    opponentScore.getValue(), myScore.decideResult(opponentScore).getName());
        } catch (final Exception e) {
            return new StatusDto("error", e.getMessage());
        }
    }

    private static ChessGameDto move(final ChessGame chessGame, final MoveDto moveDto) {
        try {
            chessGame.move(Position.create(moveDto.getSource()), Position.create(moveDto.getTarget()));
            return new ChessGameDto(chessGame);
        } catch (final Exception e) {
            return new ChessGameDto("error", e.getMessage(), chessGame);
        }
    }
}
