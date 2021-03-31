package chess.web;

import chess.dto.RoomDTO;
import chess.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.List;

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public JsonElement updateRooms(Request request, Response response) throws SQLException {
        response.type("application/json");
        List<RoomDTO> roomDTOs = roomService.updateRooms();
        return new Gson().toJsonTree(roomDTOs);
    }
}
