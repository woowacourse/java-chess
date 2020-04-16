package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.model.domain.piece.Color;
import chess.model.dto.CreateRoomDto;
import chess.model.dto.DeleteRoomDto;
import chess.model.dto.MoveDto;
import chess.model.dto.PromotionTypeDto;
import chess.model.dto.SourceDto;
import chess.model.dto.UserNameDto;
import chess.model.service.ChessGameService;
import chess.model.service.ResultService;
import chess.model.service.RoomService;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessUIController {

    private static final Gson GSON = new Gson();
    private static final ChessGameService CHESS_GAME_SERVICE = ChessGameService.getInstance();
    private static final RoomService ROOM_SERVICE = RoomService.getInstance();
    private static final ResultService RESULT_SERVICE = ResultService.getInstance();
    private static final Map<Color, String> DEFAULT_NAMES;

    static {
        Map<Color, String> defaultNames = new HashMap<>();
        defaultNames.put(Color.BLACK, "BLACK");
        defaultNames.put(Color.WHITE, "WHITE");
        DEFAULT_NAMES = Collections.unmodifiableMap(new HashMap<>(defaultNames));
    }

    public static void run() {
        staticFileLocation("/static");
        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", req.queryParams("roomId"));
            return render(model, "/start.html");
        });

        post("/choiceGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomId",
                CHESS_GAME_SERVICE.getRoomId(Integer.parseInt(req.queryParams("gameId"))));
            return render(model, "/start.html");
        });

        post("/newGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Color, String> userNames = getUserNames(req);
            model.put("gameId", CHESS_GAME_SERVICE
                .endAndNewChessGame(Integer.parseInt(req.queryParams("gameId")), userNames));
            return render(model, "/game.html");
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/room.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Color, String> userNames = getUserNames(req);
            int roomId = Integer.parseInt(req.queryParams("roomId"));
            model.put("gameId", CHESS_GAME_SERVICE.newChessGame(roomId, userNames));
            return render(model, "/game.html");
        });

        post("/followGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Color, String> userNames = getUserNames(req);
            int roomId = Integer.parseInt(req.queryParams("roomId"));
            model.put("gameId", CHESS_GAME_SERVICE.getIdBefore(roomId, userNames));
            return render(model, "/game.html");
        });

        post("/move", (req, res) -> {
            MoveDto moveDTO = GSON.fromJson(req.body(), MoveDto.class);
            return GSON.toJson(CHESS_GAME_SERVICE.move(moveDTO));
        });

        post("/path", (req, res) -> {
            SourceDto sourceDto = GSON.fromJson(req.body(), SourceDto.class);
            return GSON.toJson(CHESS_GAME_SERVICE.getPath(sourceDto));
        });

        post("/promotion", (req, res) -> {
            PromotionTypeDto promotionTypeDTO = GSON.fromJson(req.body(), PromotionTypeDto.class);
            return GSON.toJson(CHESS_GAME_SERVICE.promotion(promotionTypeDTO));
        });

        post("/board", (req, res) -> {
            MoveDto moveDTO = GSON.fromJson(req.body(), MoveDto.class);
            return GSON.toJson(CHESS_GAME_SERVICE.loadChessGame(moveDTO.getGameId()));
        });

        post("/end", (req, res) -> {
            MoveDto moveDTO = GSON.fromJson(req.body(), MoveDto.class);
            return GSON.toJson(CHESS_GAME_SERVICE.endGame(moveDTO));
        });

        get("/viewRooms", (req, res) -> GSON.toJson(ROOM_SERVICE.getUsedRooms()));

        get("/viewUsers", (req, res) -> GSON.toJson(RESULT_SERVICE.getUsers()));

        post("/createRoom", (req, res) -> {
            ROOM_SERVICE.addRoom(GSON.fromJson(req.body(), CreateRoomDto.class));
            return GSON.toJson(ROOM_SERVICE.getUsedRooms());
        });

        post("/deleteRoom", (req, res) -> {
            ROOM_SERVICE.deleteRoom(GSON.fromJson(req.body(), DeleteRoomDto.class));
            return GSON.toJson(ROOM_SERVICE.getUsedRooms());
        });

        post("/userResult", (req, res) -> GSON
            .toJson(RESULT_SERVICE.getResult(GSON.fromJson(req.body(), UserNameDto.class))));

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/result.html");
        });
    }

    private static Map<Color, String> getUserNames(Request req) {
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, getUserName(Color.BLACK, req.queryParams("BlackName")));
        userNames.put(Color.WHITE, getUserName(Color.WHITE, req.queryParams("WhiteName")));
        return userNames;
    }

    private static String getUserName(Color color, String inputName) {
        if (inputName == null || inputName.trim().isEmpty()) {
            return DEFAULT_NAMES.get(color);
        }
        return inputName;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
