package chess.controller;

import chess.dto.ScoreDto;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    ChessGameService service = new ChessGameService();

    public void run() {
        staticFileLocation("/templates");

        get("/", (req, res) ->
                new HandlebarsTemplateEngine().render(new ModelAndView(Map.of(), "start.html"))
        );

        get("/game", (req, res) -> {
            service.init();
            return render(generateResponse());
        });

        get("/start", (req, res) -> {
            try {
                service.start();
                return render(generateResponse());
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

        get("/end", (req, res) -> {
            try {
                service.end();
                return render(generateResponse());
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

        get("/restart", (req, res) -> {
            try {
                service.restart();
                return render(generateResponse());
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

        get("/save", (req, res) -> {
            try {
                service.save();
                return render(generateResponse());
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

        get("/status", (req, res) -> {
            try {
                ScoreDto score = service.status();
                return render(generateResponse(score));
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

        post("/move", (req, res) -> {
            try {
                service.move(req.queryParams("from"), req.queryParams("to"));
                return render(generateResponse());
            } catch (RuntimeException e) {
                return render(generateResponse(e.getMessage()));
            }
        });

    }

    private String render(Map<String, Object> response) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(response, "chessGame.html"));
    }

    private Map<String, Object> generateResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("pieces", service.getPiecesByUnicode());
        return response;
    }

    private Map<String, Object> generateResponse(String errorMessage) {
        Map<String, Object> response = generateResponse();
        response.put("error", errorMessage);
        return response;
    }

    private Map<String, Object> generateResponse(ScoreDto score) {
        Map<String, Object> response = generateResponse();
        response.put("score", score);
        return response;
    }

}
