package chess.web;

import chess.handler.CanNotMoveExceptionHandler;
import chess.handler.EndGameExceptionHandler;
import chess.handler.NoSuchElementExceptionHandler;
import chess.handler.NumberFormatExceptionHandler;
import chess.handler.exception.AlreadyEndGameException;
import chess.service.ChessService;
import chess.web.dto.ChessBoardResponse;
import chess.web.dto.DefaultResponse;
import chess.web.dto.MoveRequest;
import chess.web.dto.MoveResponse;
import chess.web.dto.SavedGameBundleResponse;
import chess.web.dto.TilesDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String toJson(DefaultResponse<?> defaultResponse) {
        return gson.toJson(defaultResponse);
    }

    public void run() {
        //home
        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            TilesDto tilesDto = chessService.getEmptyBoard();
            model.put("tilesDto", tilesDto);
            return render(model, "index.html");
        });

        //start 새게임 시작 및 저장
        post("/chessboard", (request, response) -> {
            ChessBoardResponse chessBoardResponse = chessService.save();

            return toJson(DefaultResponse.CREATED(chessBoardResponse));
        });

        //get saved list
        get("/chessboard", (request, response) -> {
            SavedGameBundleResponse savedGameBundleResponse = chessService.loadAllSavedGames();
            return toJson(DefaultResponse.OK(savedGameBundleResponse));
        });

        //load chessboard
        get("/chessboard/:id", (request, response) -> {
            Long targetId = Long.parseLong(request.params("id"));
            return toJson(DefaultResponse.OK(chessService.loadSavedGame(targetId)));
        });

        // move
        post("/chessboard/move", (request, response) -> {
            MoveRequest moveRequest = gson.fromJson(request.body(), MoveRequest.class);

            MoveResponse moveResponse = chessService.move(moveRequest);
            return toJson(DefaultResponse.OK(moveResponse));
        });

        //surrender
        post("/surrender", (request, response) -> {

            return null;
        });

        //end
        delete("/end", (request, response) -> {

            return null;
        });

        exception(IllegalArgumentException.class, new CanNotMoveExceptionHandler());
        exception(AlreadyEndGameException.class, new EndGameExceptionHandler());
        exception(NumberFormatException.class, new NumberFormatExceptionHandler());
        exception(NoSuchElementException.class, new NoSuchElementExceptionHandler());
    }

}
