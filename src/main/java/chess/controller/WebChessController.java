package chess.controller;

import chess.domain.ChessBoards;
import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.MoveStatusDto;
import chess.domain.dto.PositionDto;
import chess.service.ChessService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.get;

public class WebChessController {
    private final ObjectMapper mapper;
    private final ChessService chessService;

    public WebChessController() {
        mapper = new ObjectMapper();
        chessService = new ChessService();
    }

    public void run() {
        ChessBoards chessBoards = new ChessBoards();
        staticFileLocation("static");
        chessMain();
        startChess(chessBoards);
        showRoute(chessBoards);
        movePiece(chessBoards);
        loadChess(chessBoards);
        exitChess();
    }

    private void chessMain() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void startChess(ChessBoards chessBoards) {
        get("/start", (req, res) -> {
            ChessRoomDto chessRoomDto = chessService.startChess(chessBoards);
            return mapper.writeValueAsString(chessRoomDto);
        });
    }

    private void showRoute(ChessBoards chessBoards) {
        post("/route", (req, res) -> {
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {
            });
            List<PositionDto> routes = chessService.showRoutes(map.get("source"), chessBoards);
            return mapper.writeValueAsString(routes);
        });
    }

    private void movePiece(ChessBoards chessBoards) {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {
                });
                ChessRoomDto chessRoomDto = chessService.movePiece(new MoveStatusDto(map.get("source"), map.get("target"), map.get("turn")), chessBoards);
                boolean isAliveAllKings = chessService.isAliveAllKings(chessBoards);
                model.put("chessRoomInfo", chessRoomDto);
                model.put("isAliveAllKings", isAliveAllKings);
                return mapper.writeValueAsString(model);
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void loadChess(ChessBoards chessBoards) {
        get("/load", (req, res) -> {
            try {
                ChessRoomDto chessRoomDto = chessService.loadChess(Integer.valueOf(req.queryParams("roomNo")), chessBoards);
                return mapper.writeValueAsString(chessRoomDto);
            } catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void exitChess() {
        get("/exit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                chessService.exitChess(Integer.parseInt(req.queryParams("roomNo")));
                model.put("success", "방을 나갔습니다.");
                return mapper.writeValueAsString(model);
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
