package chess.cotroller;

import chess.dto.RoomDto;
import chess.result.Result;
import chess.service.RoomService;
import chess.utils.validator.RoomValidator;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RoomController {
    private RoomService roomService = new RoomService();

    public Object create(Request request, Response response) {
        String roomName = request.queryParams("roomName");
        int userId = Integer.parseInt(request.queryParams("userId"));

        RoomValidator.checkRoomName(roomName);
        RoomValidator.checkUserId(userId);

        RoomDto roomDto = new RoomDto();
        roomDto.setWhiteUserId(userId);
        roomDto.setName(roomName);

        Result result = roomService.create(roomDto);

        if (result.isSuccess()) {
            return result.getObject();
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }

    public Object join(Request request, Response response) {
        String roomName = request.queryParams("roomName");
        int userId = Integer.parseInt(request.queryParams("userId"));

        RoomValidator.checkRoomName(roomName);
        RoomValidator.checkUserId(userId);

        Result result = roomService.join(roomName, userId);
        if (result.isSuccess()) {
            return result.getObject();
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }

    public Object exit(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));
        int userId = Integer.parseInt(request.queryParams("userId"));

        return roomService.exit(roomId, userId);
    }

    public Object quit(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));

        return roomService.quit(roomId);
    }

    public Object status(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));
        Result result = roomService.status(roomId);
        if (result.isSuccess()) {
            return new Gson().toJson(result.getObject());
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }
}
