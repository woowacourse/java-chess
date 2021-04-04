package chess.controller;

import chess.domain.dto.ChessRoomDto;
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
        staticFileLocation("static");
        chessMain();
        startChess();
        showRoute();
        movePiece();
        loadChess();
        exitChess();
    }

    private void chessMain() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void startChess() {
        get("/start", (req, res) -> {
            ChessRoomDto chessRoomDto = chessService.startChess();
            return mapper.writeValueAsString(chessRoomDto);
        });
    }

    private void showRoute() {
        post("/route", (req, res) -> {
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
            List<PositionDto> routes = chessService.showRoutes(map);
            return mapper.writeValueAsString(routes);
        });
    }

    private void movePiece() {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
                chessService.movePiece(map, model);
                return mapper.writeValueAsString(model);
            }catch (Exception e) {
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void loadChess() {
        get("/load", (req, res) -> {
            try {
                ChessRoomDto chessRoomDto = chessService.loadChess(Integer.valueOf(req.queryParams("roomNo")));
                return mapper.writeValueAsString(chessRoomDto);
            }catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void exitChess() {
        get("/exit", (req, res) -> {
            return chessService.exitChess(Integer.parseInt(req.queryParams("roomNo")));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
