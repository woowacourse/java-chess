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
    private final Gson gson;

    public ChessWebController() {
        chessService = new ChessService();
        roomService = new RoomService();
        gson = new Gson();
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
            final Map<String, Object> model = chessService.startGame(req.queryParams("room"));
            return gson.toJson(model);
        });

        before("/protected/*", (req, res) -> {
            if (!roomService.isRoomExist(req.params(":roomName")) && !roomService.isRoomExist(
                    req.queryParams("room"))) {
                Map<String, Object> model = new HashMap<>();
                model.put("roomExist", false);
                halt(401, gson.toJson(model));
            }
        });

        path("/protected", () -> {

            get("/piece", (req, res) -> {
                final Map<String, Object> model = chessService.findAllPiece(req.queryParams("room"));
                return gson.toJson(model);
            });

            get("/score", (req, res) -> {
                final Map<String, Object> model = chessService.findScore(req.queryParams("room"));
                return gson.toJson(model);
            });

            get("/turn", (req, res) -> {
                final Map<String, Object> model = chessService.findCurrentTurn(req.queryParams("room"));
                return gson.toJson(model);
            });

            get("/result", (req, res) -> {
                final Map<String, Object> model = chessService.result(req.queryParams("room"));
                return gson.toJson(model);
            });

            post("/move", (req, res) -> {
                final String[] splitBody = req.body().split(" ");
                final Map<String, Object> model = chessService.move(
                        req.queryParams("room"),
                        splitBody[0],
                        splitBody[1]
                );
                return gson.toJson(model);
            });

            post("/end", (req, res) -> {
                final Map<String, Object> model = chessService.endGame(req.queryParams("room"));
                return gson.toJson(model);
            });

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
