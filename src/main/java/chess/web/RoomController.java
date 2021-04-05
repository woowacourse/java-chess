package chess.web;

import chess.dto.RoomDTO;
import chess.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    private static final Gson GSON = new Gson();
    private static final String RESPONSE_JSON = "application/json";

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public JsonElement showRooms(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        List<RoomDTO> roomDTOs = roomService.findAllRooms()
                .stream()
                .map(RoomDTO::from)
                .collect(Collectors.toList());
        return GSON.toJsonTree(roomDTOs);
    }

    public JsonElement insertRoom(Request request, Response response) throws SQLException {
        response.type(RESPONSE_JSON);
        RoomDTO requestDTO = GSON.fromJson(request.body(), RoomDTO.class);
        String roomName = requestDTO.getName();
        roomService.insertRoom(roomName);
        RoomDTO responseDTO = RoomDTO.from(roomService.findLatestRoom());
        return GSON.toJsonTree(responseDTO);
    }
}
