package chess.controller;

import chess.dto.ScoreDto;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private static final String CHESS_GAME_URL = "chessGame.html";
    private static final String START_URL = "start.html";

    private final ChessGameService service = new ChessGameService();

    public void run() {
        staticFileLocation("/templates");

        get("/", (req, res) -> render(Map.of(), START_URL));

        get("/game", (req, res) -> executeCommand(service::init));

        get("/start", (req, res) -> executeCommand(service::start));

        get("/end", (req, res) -> executeCommand(service::end));

        get("/restart", (req, res) -> executeCommand(service::restart));

        get("/save", (req, res) -> executeCommand(service::save));

        get("/status", (req, res) -> status());

        post("/move", (req, res) -> executeCommand(
                () -> service.move(req.queryParams("from"), req.queryParams("to")))
        );

    }

    private String executeCommand(Runnable command) {
        try {
            command.run();
            return render(generateResponse(), CHESS_GAME_URL);
        } catch (RuntimeException e) {
            return render(generateResponse(e.getMessage()), CHESS_GAME_URL);
        }
    }

    private String status() {
        try {
            ScoreDto score = service.status();
            return render(generateResponse(score), CHESS_GAME_URL);
        } catch (RuntimeException e) {
            return render(generateResponse(e.getMessage()), CHESS_GAME_URL);
        }
    }

    private String render(Map<String, Object> response, String url) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(response, url));
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
