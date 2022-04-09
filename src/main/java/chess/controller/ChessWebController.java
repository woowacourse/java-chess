package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGameService;
import chess.controller.dto.ErrorDto;
import chess.controller.dto.MoveDto;
import chess.domain.board.strategy.BoardGenerationStrategy;
import com.google.gson.Gson;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessGameService chessGameService;
    private final Gson gson = new Gson();

    public ChessWebController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void start(BoardGenerationStrategy strategy) {
        port(8888);
        staticFileLocation("/static");
        get("/", (req, res) -> {
            Map<String, String> model = chessGameService.loadChessGame().getBoard();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            return gson.toJson(chessGameService.startChessGame(strategy));
        });

        post("/move", (req, res) -> {
            try {
                MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
                return gson.toJson(chessGameService.move(moveDto.getFrom(), moveDto.getTo()));
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });

        get("/status", (req, res) -> {
            try {
                return gson.toJson(chessGameService.createScore());
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });

        get("/end", (req, res) -> {
            try {
                return gson.toJson(chessGameService.end());
            } catch (Exception e) {
                return gson.toJson(new ErrorDto(e.getMessage()));
            }
        });
    }

    private static String render(Map<String, String> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
