package chess.controller;

import chess.service.ChessService;
import chess.service.dto.*;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {
    private final ChessService chessService;

    public ChessWebController(final ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }


    public void run() {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        get("/games", (request, response) -> {
            TilesDto tilesDto = chessService.emptyBoard();
            Map<String, Object> model = new HashMap<>();
            model.put("tilesDto", tilesDto);
            return render(model, "board.html");
        });


/*        get("/games/start", (request, response) -> {
            PiecesStatusDto piecesStatusDto = chessService.initializeGame();
            return toJson(piecesStatusDto);
        });*/



        post("/games", (request, response) -> {
            ChessSaveRequestDto requestDto =  new Gson().fromJson(request.body(), ChessSaveRequestDto.class);
            GameStatusDto responseDto = chessService.startChess(requestDto);
            return toJson(responseDto);
        });

        get("/games/:name", (request, response) -> {
            String name = request.params(":name");
            GameStatusDto responseDto = chessService.loadChess(name);
            return toJson(responseDto);

        });

        put("/games", (request, response) -> {
            GameStatusRequestDto requestDto = new Gson().fromJson(request.body(), GameStatusRequestDto.class);
            chessService.changeGameStatus(requestDto);
            return toJson(requestDto);
        });

        put("/pieces", (request, response) -> {
            MoveRequestDto requestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
            MoveResponseDto responseDto = chessService.movePiece(requestDto);
            return toJson(responseDto);
        });
    }
}
