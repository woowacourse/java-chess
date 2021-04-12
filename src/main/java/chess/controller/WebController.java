package chess.controller;

import chess.dto.MovablePositionDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class WebController {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final ChessService chessService;

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

    public String createRoom(Request request, Response response) throws JsonProcessingException {
        String roomNumber = request.params(":room");
        return OBJECT_MAPPER.writeValueAsString(chessService.loadRoom(roomNumber));
    }

    public String move(Request request, Response response) throws JsonProcessingException {
        MoveRequestDto moveRequestDto = OBJECT_MAPPER.readValue(request.body(), MoveRequestDto.class);
        try {
            return OBJECT_MAPPER.writeValueAsString(chessService.move(moveRequestDto));
        } catch (Exception e) {
            return OBJECT_MAPPER.writeValueAsString(chessService.loadRoom(moveRequestDto.getRoomId()));
        }
    }

    public String movablePosition(Request request, Response response) throws JsonProcessingException {
        MovablePositionDto movablePositionDto = OBJECT_MAPPER.readValue(request.body(), MovablePositionDto.class);
        return OBJECT_MAPPER.writeValueAsString(chessService.movablePosition(movablePositionDto));
    }

    public String score(Request request, Response response) throws JsonProcessingException {
        MovablePositionDto movablePositionDto = OBJECT_MAPPER.readValue(request.body(), MovablePositionDto.class);
        return OBJECT_MAPPER.writeValueAsString(chessService.boardStatusDto(movablePositionDto.getRoomId()));
    }

    public String clear(Request request, Response response) {
        String roomNumber = request.params(":room");
        chessService.deleteRoom(roomNumber);
        response.redirect("/");
        return null;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
