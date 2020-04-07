package chess.controller;

import static chess.util.HandlebarsUtil.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.room.Room;
import chess.service.RoomService;
import spark.Route;

public class RoomController {
	public static final String BASIC_URL = "/rooms";
	public static final String ENTER_ROOM_URL = BASIC_URL + "/enter";
	public static final String CREATE_ROOM_URL = BASIC_URL + "/create";
	public static final String REMOVE_ROOM_URL = BASIC_URL + "/remove";
	public static Route enterRoom = (request, response) -> {
		Map<String, Object> model = new HashMap<>();
		model.put("roomId", request.queryParams("roomId"));

		return render(model, "game.html");
	};
	public static Route getAllRoom = (request, response) -> {
		Map<String, Object> model = new HashMap<>();

		RoomService roomService = RoomService.getInstance();
		List<Room> rooms = roomService.findAllRoom();
		model.put("rooms", rooms);

		return render(model, "index.html");
	};
	public static Route createRoom = (request, response) -> {
		RoomService roomService = RoomService.getInstance();
		String param = request.queryParams("roomName");
		roomService.addRoom(param);

		// TODO : 페이지 전체를 불러오기 때문에 비효율적이다. ajax로 하면 더 효율적이다.
		response.redirect("/rooms");
		return null;
	};
	// TODO : add랑 똑같은 로직인데 왜 새로고침해야 보일까?
	public static Route removeRoom = (request, response) -> {
		RoomService roomService = RoomService.getInstance();
		roomService.removeRoom(request.queryParams("roomId"));

		response.redirect("/rooms");
		return null;
	};

	private RoomController() {
	}
}
