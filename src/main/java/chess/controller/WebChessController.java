package chess.controller;

import chess.dto.ChessGameDTO;
import chess.dto.RoomsDTO;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private Gson gson = null;
    private final ChessGameService chessGameService;

    public WebChessController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/refreshChessGame", (req, res) -> {
            try {
                String id = req.queryParams("id");
                ChessGameDTO chessGameDTO = chessGameService.refreshChessGame(id);
                return gson.toJson(chessGameDTO);
            } catch (IllegalArgumentException illegalArgumentException) {
                res.status(400);
                return generateJsonMessage("게임을 갱신하지 못했습니다.");
            }
        });

        get("/loadChessGame", (req, res) -> {
            try {
                String id = req.queryParams("id");
                ChessGameDTO chessGameDTO = chessGameService.loadChessGame(id);
                return gson.toJson(chessGameDTO);
            } catch (IllegalArgumentException illegalArgumentException) {
                res.status(400);
                return generateJsonMessage("게임을 로드하지 못했습니다.");
            }
        });

        get("/selectPiece", (req, res) -> {
            try {
                String id = req.queryParams("id");
                String selected = req.queryParams("position");
                chessGameService.selectPiece(id, selected);
                return "";
            } catch (IllegalArgumentException illegalArgumentException) {
                res.status(400);
                return generateJsonMessage("선택 할 수 없습니다.");
            }
        });

        get("/movePiece", (req, res) -> {
            try {
                String id = req.queryParams("id");
                String selected = req.queryParams("selected");
                String target = req.queryParams("target");
                ChessGameDTO chessGameDTO = chessGameService.moveChessGame(id, selected, target);
                return gson.toJson(chessGameDTO);
            } catch (IllegalArgumentException illegalArgumentException) {
                res.status(400);
                return generateJsonMessage("움직일 수 없습니다.");

            }
        });

        post("/createRoom", (req, res) -> {
            try {
                chessGameService.createRoom(req.body());
                RoomsDTO roomsDTO = chessGameService.getTotalRoom();
                return gson.toJson(roomsDTO);
            } catch (IllegalArgumentException e) {
                res.status(400);
                return generateJsonMessage(e.getMessage());
            }
        });

        get("/enterRoom", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/getTotalRoom", (req, res) -> {
            try {
                RoomsDTO roomsDTO = chessGameService.getTotalRoom();
                return gson.toJson(roomsDTO);
            } catch (IllegalArgumentException e) {
                res.status(400);
                return generateJsonMessage(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private String generateJsonMessage(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("message", message);
        return jsonObject.toString();
    }
}
