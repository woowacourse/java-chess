package chess.controller;

import chess.service.ChessService;
import chess.service.dto.MoveRequestDto;
import chess.service.dto.MoveResponseDto;
import chess.service.dto.PiecesStatusDto;
import chess.service.dto.TilesDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

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
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "home.html");
        });

        get("/start", (request, response) -> {
            PiecesStatusDto piecesStatusDto = chessService.initializeGame();
            return toJson(piecesStatusDto);
        });

        post("/start", (request, response) -> {
            TilesDto tilesDto = chessService.emptyBoard();
            Map<String, Object> model = new HashMap<>();
            model.put("tilesDto", tilesDto);
            return render(model, "board.html");
        });

        post("/move", (request, response) -> {
            MoveRequestDto requestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
            MoveResponseDto responseDto = chessService.movePiece(requestDto);
            return toJson(responseDto);
        });
    }
}
