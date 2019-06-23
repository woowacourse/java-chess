package chess.controller;

import chess.dto.RoomDto;
import chess.service.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private final RoomService roomService;

    public MainController(final RoomService roomService) {
        this.roomService = roomService;
    }

    public Map<String, Object> processMain() {
        Map<String, Object> model = new HashMap<>();
        List<RoomDto> roomDtos = roomService.findAllByOngoing();
        model.put("roomDtos", roomDtos);
        return model;
    }
}
