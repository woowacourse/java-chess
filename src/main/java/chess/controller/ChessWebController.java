package chess.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import chess.service.ChessService;
import chess.service.RoomService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessService chessService;
    private final RoomService roomService;

    public ChessWebController() {
        chessService = new ChessService();
        roomService = new RoomService();
    }

    public void run() {

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "index.html"));
        });

        path("/rooms", () -> {
            get("/:name", roomService::findPage);

            post("/:name", roomService::createRoom);

            delete("/:name", roomService::deleteRoom);

            get("/:name/pieces", chessService::findAllPiece);

            post("/:name/pieces", chessService::initPiece);

            put("/:name/pieces", chessService::move);

            get("/:name/scores", chessService::findScore);

            get("/:name/turn", roomService::findCurrentTurn);

            get("/:name/result", chessService::result);
        });
    }
}
