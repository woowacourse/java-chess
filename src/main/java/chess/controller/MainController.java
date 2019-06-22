package chess.controller;

import chess.dto.RoomDto;
import chess.service.RoomService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class MainController {
    private final RoomService roomService;

    public MainController(final RoomService roomService) {
        this.roomService = roomService;
    }

    public Object main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<RoomDto> roomDtos = roomService.findAllByStatus(false);
        model.put("roomDtos", roomDtos);
        return render(model, "main.html");
    }
}
