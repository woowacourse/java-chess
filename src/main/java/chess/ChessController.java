package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.ExceptionResponseDto;
import chess.dto.MoveDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    public static final int STATUS_BAD_REQUEST = 400;
    public static final int FIXED_ROOM_ID = 1;

    private final ChessService chessService;
    private final Gson gson;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
        this.gson = new Gson();
    }

    public void run() {
        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/start", (req, res) -> {
            return gson.toJson(chessService.startNewGame(FIXED_ROOM_ID));
        });

        get("/load", (req, res) -> {
            return gson.toJson(chessService.load(FIXED_ROOM_ID));
        });

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            return gson.toJson(chessService.move(FIXED_ROOM_ID, moveDto));
        });

        get("/status", (req, res) -> {
            return gson.toJson(chessService.status(FIXED_ROOM_ID));
        });

        exception(IllegalStateException.class, (e, req, res) -> {
            handleError(res, e.getMessage());
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            handleError(res, e.getMessage());
        });
    }

    private void handleError(Response res, String exceptionMessage) {
        res.status(STATUS_BAD_REQUEST);
        res.body(gson.toJson(new ExceptionResponseDto(exceptionMessage)));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
