package chess.controller;

import chess.domain.ChessGame;
import chess.dto.MovablePositionDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WebController {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final ChessService chessService;
    private ChessGame chessGame;

    public WebController() {
        this.chessService = new ChessService();
    }

    public String mainPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "main.html");
    }

    public String startGame(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("roomId", request.queryParams("room"));
        return render(model, "index.html");
    }

    public String createRoom(Request request, Response response) throws JsonProcessingException, SQLException {
        chessGame = new ChessGame();
        String roomNumber = request.params(":room");
        return OBJECT_MAPPER.writeValueAsString(chessService.loadRoom(chessGame, roomNumber));
    }

    public String move(Request request, Response response) throws JsonProcessingException {
        try {
            MoveRequestDto moveRequestDto = OBJECT_MAPPER.readValue(request.body(), MoveRequestDto.class);
            return OBJECT_MAPPER.writeValueAsString(chessService.movePiece(chessGame, moveRequestDto));
        } catch (Exception e) {
            return OBJECT_MAPPER.writeValueAsString(chessService.boardDto(chessGame));
        }
    }

    public String movablePosition(Request request, Response response) throws JsonProcessingException {
        MovablePositionDto movablePositionDto = OBJECT_MAPPER.readValue(request.body(), MovablePositionDto.class);
        return OBJECT_MAPPER.writeValueAsString(chessService.movablePosition(chessGame, movablePositionDto.getTarget()));
    }

    public String score(Request request, Response response) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(chessService.boardStatusDto(chessGame));
    }

    public String clear(Request request, Response response) throws SQLException {
        String roomNumber = request.params(":room");
        chessService.deleteRoom(roomNumber);
        response.redirect("/");
        return null;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
