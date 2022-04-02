package chess.controller;

import chess.chessgame.ChessGame;
import chess.chessgame.MovingPosition;
import chess.dto.ScoreDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    public void run() {
        staticFileLocation("/templates");
        final ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            try {
                return render(new HashMap<>());
            } catch (IllegalArgumentException e) {
                return render(generateResponse(chessGame, e.getMessage()));
            }
        });

        get("/start", (req, res) -> {
            try {
                chessGame.start();
                return render(generateResponse(chessGame));
            } catch (RuntimeException e) {
                return render(generateResponse(chessGame, e.getMessage()));
            }
        });

        post("/move", (req, res) -> {
            try {
                chessGame.move(new MovingPosition(req.queryParams("from"), req.queryParams("to")));
                return render(generateResponse(chessGame));
            } catch (RuntimeException e) {
                return render(generateResponse(chessGame, e.getMessage()));
            }
        });

        get("/status", (req, res) -> {
            try {
                chessGame.computeScore();
                return render(generateResponse(chessGame, chessGame.computeScore()));
            } catch (RuntimeException e) {
                return render(generateResponse(chessGame, e.getMessage()));
            }
        });

        get("/end", (req, res) -> {
            try {
                chessGame.end();
                return render(generateResponse(chessGame));
            } catch (RuntimeException e) {
                return render(generateResponse(chessGame, e.getMessage()));
            }
        });
    }

    private String render(Map<String, Object> response) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(response, "chessGame.html"));
    }

    private Map<String, Object> generateResponse(ChessGame chessGame) {
        Map<String, Object> response = new HashMap<>();
        response.put("pieces", chessGame.getPiecesByUnicode());
        return response;
    }

    private Map<String, Object> generateResponse(ChessGame chessGame, String errorMessage) {
        Map<String, Object> response = generateResponse(chessGame);
        response.put("error", errorMessage);
        return response;
    }

    private Map<String, Object> generateResponse(ChessGame chessGame, ScoreDto score) {
        Map<String, Object> response = generateResponse(chessGame);
        response.put("score", score);
        return response;
    }

}
