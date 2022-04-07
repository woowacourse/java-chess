package chess.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import chess.StatusCode;
import chess.dto.ErrorResponseDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import chess.service.RoomService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private static final String ERROR = "error";

    private final ChessService chessService;
    private final RoomService roomService;

    public ChessWebController() {
        chessService = new ChessService();
        roomService = new RoomService();
    }

    public void run() {

        get("/", (req, res) ->
                render(new HashMap<>(), "index.html")
        );

        path("/rooms", () -> {
            get("/:name", (req, res) -> {
                final boolean roomExist = roomService.isExistRoom(extractRoomName(req));
                if (!roomExist) {
                    res.redirect("/");
                }
                return render(new HashMap<>(), "board.html");
            });

            post("/:name", (req, res) ->
                    roomService.createRoom(extractRoomName(req))
            );

            delete("/:name", (req, res) -> {
                final String json = roomService.deleteRoom(extractRoomName(req));
                handleError(json, res);
                return json;
            });

            get("/:name/pieces", (req, res) -> {
                final String json = chessService.findAllPiece(extractRoomName(req));
                handleError(json, res);
                return json;
            });

            post("/:name/pieces", (req, res) -> {
                final String json = chessService.initPiece(extractRoomName(req));
                handleError(json, res);
                return json;
            });

            put("/:name/pieces", (req, res) -> {
                final String requestBody = req.body();
                final MoveRequestDto requestDto = MoveRequestDto.from(requestBody);
                final String json = chessService.move(extractRoomName(req), requestDto);
                handleError(json, res);
                return json;
            });

            get("/:name/scores", (req, res) -> {
                final String json = chessService.findScore(extractRoomName(req));
                handleError(json, res);
                return json;
            });

            get("/:name/turn", (req, res) -> {
                final String json = roomService.findCurrentTurn(extractRoomName(req));
                handleError(json, res);
                return json;
            });

            get("/:name/result", (req, res) -> {
                final String json = chessService.result(extractRoomName(req));
                handleError(json, res);
                return json;
            });
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private String extractRoomName(Request req) {
        return req.params(":name");
    }

    private void handleError(final String json, final Response res) {
        if (!json.contains(ERROR)) {
            return;
        }
        final StatusCode statusCode = ErrorResponseDto.toStatusCode(json);
        res.status(statusCode.getValue());
    }
}
