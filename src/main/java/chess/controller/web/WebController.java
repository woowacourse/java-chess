package chess.controller.web;

import chess.controller.web.dto.ChessGameResponseDto;
import chess.controller.web.dto.MessageResponseDto;
import chess.controller.web.dto.MoveRequestDto;
import chess.controller.web.dto.MoveResponseDto;
import chess.controller.web.dto.RunningGameResponseDto;
import chess.controller.web.dto.ScoreResponseDto;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerBundle;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int HTTP_STATUS_OK = 200;
    private static final int HTTP_STATUS_ERROR = 400;

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void start() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        gameRouting();

        exceptionHandling();
    }

    private void gameRouting() {

        get("/user", (request, response) -> {
            response.type("application/json; charset=utf-8");
            ChessGameManagerBundle runningGames = chessService.findRunningGames();
            return new RunningGameResponseDto(runningGames.getIdAndNextTurn());
        }, GSON::toJson);

        path("/game", () -> {
            get("/start", (request, response) -> {
                response.type("application/json; charset=utf-8");
                return new ChessGameResponseDto(chessService.start());
            }, GSON::toJson);

            get("/score/:id", (request, response) -> {
                long id = Long.parseLong(request.params("id"));
                response.type("application/json; charset=utf-8");
                return new ScoreResponseDto(chessService.getStatistics(id));
            }, GSON::toJson);

            get("/load/:id", (request, response) -> {
                try {
                    long id = Long.parseLong(request.params("id"));
                    ChessGameManager load = chessService.load(id);
                    return new ChessGameResponseDto(load);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("게임 id는 숫자값이어야 합니다.");
                }
            }, GSON::toJson);

            post("/move", (request, response) -> {
                MoveRequestDto moveRequestDto = GSON.fromJson(request.body(), MoveRequestDto.class);
                chessService.move(moveRequestDto);
                response.type("application/json; charset=utf-8");
                return new MoveResponseDto(chessService.isEnd(moveRequestDto.getId()), chessService.nextColor(moveRequestDto.getId()));
            }, GSON::toJson);
        });
    }

    private void exceptionHandling() {
        exception(RuntimeException.class, (e, request, response) -> {
            response.type("application/json; charset=utf-8");
            response.status(HTTP_STATUS_ERROR);
            e.printStackTrace();
            response.body(GSON.toJson(new MessageResponseDto(e.getMessage())));
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
