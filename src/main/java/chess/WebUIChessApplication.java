package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import chess.dto.web.RoomDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson GSON = new Gson();
    private static final ChessService CHESS_SERVICE = new ChessService();

    public static void main(String[] args) {

        Spark.staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", CHESS_SERVICE.openedRooms());
            return render(model, "lobby.html");
        });

        post("/room", "application/json", (req, res) -> {
            try {
                RoomDto newRoom = GSON.fromJson(req.body(), RoomDto.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("result", "success");
                jsonObject.addProperty("roomId", CHESS_SERVICE.create(newRoom));
                return GSON.toJson(jsonObject);
            } catch (IllegalArgumentException e) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("result", "fail");
                jsonObject.addProperty("message", e.getMessage());
                return GSON.toJson(jsonObject);
            }
        });

        put("/room", (req, res) -> {
            Map<String, String> body = GSON.fromJson(req.body(), HashMap.class);
            CHESS_SERVICE.close(body.get("id"));

            return GSON.toJson("success");
        });

        get("/room/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", CHESS_SERVICE.latestBoard(req.params("id")));
            model.put("roomId", req.params(":id"));
            model.put("userInfo", CHESS_SERVICE.usersInRoom(req.params("id")));
            return render(model, "index.html");
        });

        get("/room/:id/statistics", "application/json",
            (req, res) -> GSON.toJson(CHESS_SERVICE.usersInRoom(req.params("id"))));

        put("/room/:id/start", (req, res) -> GSON.toJson(CHESS_SERVICE.start(req.params(":id"))));

        put("/room/:id/exit", (req, res) -> {
            CHESS_SERVICE.exit(req.params("id"));

            return GSON.toJson("success");
        });

        put("/room/:id/move", "application/json", (req, res) -> {
            Map<String, String> body = GSON.fromJson(req.body(), HashMap.class);

            return GSON.toJson(
                CHESS_SERVICE.move(req.params("id"), body.get("source"), body.get("destination")));
        });

        get("/room/:id/getGameStatus", "application/json",
            (req, res) -> GSON.toJson(CHESS_SERVICE.gameStatus(req.params("id"))));

        get("/room/:id/movablePoints/:point", "application/json", (req, res) ->
            GSON.toJson(CHESS_SERVICE.movablePoints(req.params("id"), req.params("point"))));
    }

    private static String render(Map<String, Object> model, String viewName) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, viewName));
    }
}
