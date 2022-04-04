package chess.controller;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.post;

import chess.service.ChessService;
import chess.service.RoomService;
import com.google.gson.Gson;
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
            return render(model, "index.html");
        });

        get("/room/:roomName", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (roomService.isRoomExist(req.params(":roomName"))) {
                model.put("roomName", req.params(":roomName"));
                model.put("roomExist", true);
                return render(model, "board.html");
            }
            model.put("roomExist", false);
            return render(model, "board.html");
        });

        post("/start", (req, res) -> {
            if (!roomService.isRoomExist(req.queryParams("room"))) {
                roomService.createRoom(req.queryParams("room"));
            }
            return chessService.startGame(req.queryParams("room")).toJson();
        });

        before("/protected/*", (req, res) -> {
            if (!roomService.isRoomExist(req.params(":roomName")) && !roomService.isRoomExist(
                    req.queryParams("room"))) {
                Map<String, Object> model = new HashMap<>();
                model.put("roomExist", false);
                final Gson gson = new Gson();
                halt(401, gson.toJson(model));
            }
        });

        path("/protected", () -> {

            get("/piece", (req, res) ->
                    chessService.findAllPiece(req.queryParams("room")).toJson());

            get("/score", (req, res) ->
                    chessService.findScore(req.queryParams("room")).toJson());

            get("/turn", (req, res) ->
                    chessService.findCurrentTurn(req.queryParams("room")).toJson());

            get("/result", (req, res) ->
                    chessService.result(req.queryParams("room")).toJson());

            post("/move", (req, res) -> {
                final String[] splitBody = req.body().split(" ");
                return chessService.move(
                        req.queryParams("room"),
                        splitBody[0],
                        splitBody[1]
                ).toJson();
            });

            post("/end", (req, res) ->
                    chessService.endGame(req.queryParams("room")).toJson());

            post("/exit", (req, res) -> {
                roomService.deleteRoom(req.queryParams("room"));
                return null;
            });
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
