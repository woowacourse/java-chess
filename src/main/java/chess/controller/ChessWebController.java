package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.dto.GameDto;
import chess.dto.ScoreDto;
import chess.service.ChessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final ChessService chessService;

    public ChessWebController() {
        this.chessService = new ChessService();
    }

    public void run() {
        get("/", (req, res) -> {
            return render(new HashMap<>(), "chess.html");
        });

        get("/start/:gamenumber", (req, res) -> {
            GameDto gameDto = chessService.getGame();
            String game = objectMapper.writeValueAsString(gameDto);
            return game;
        });

        get("/end/:gamenumber", (req, res) -> {
            chessService.endGame();
            return render(new HashMap<>(), "chess.html");
        });

        get("/status/:gamenumber", (req, res) -> {
            ScoreDto scoreDto = chessService.getScore();
            String score = objectMapper.writeValueAsString(scoreDto);
            return score;
        });

        post("/move/:gamenumber", (req, res) -> {
            chessService.movePiece(new CommandDto(req.body()));

            GameDto gameDto = chessService.getGame();
            String game = objectMapper.writeValueAsString(gameDto);
            return game;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
