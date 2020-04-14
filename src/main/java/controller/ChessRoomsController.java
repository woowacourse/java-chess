package controller;

import dao.exceptions.DaoNoneSelectedException;
import dto.RoomDto;
import service.ChessRoomsService;
import spark.Response;
import spark.Spark;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessRoomsController {
	private static final ChessRoomsController ROOMS_CONTROLLER;
	public static final String PATH = "/chess/rooms";
	private static final String STATIC_PATH = "/rooms.html";
	private static final String EMPTY = "";
	private static final String SLASH = "/";
	private static final String ROOM_NAME_OF_FORM = "room_name";
	private static final String ROOMS_KEY = "rooms";

	private final ChessRoomsService chessRoomsService;

	static {
		ROOMS_CONTROLLER = new ChessRoomsController(ChessRoomsService.getInstance());
	}

	private ChessRoomsController(final ChessRoomsService chessRoomsService) {
		this.chessRoomsService = chessRoomsService;
	}

	public void run() {
		routeGetMethod();
		routePostMethod();
	}

	private void routeGetMethod() {
		Spark.get(PATH, (request, response) -> {
			final List<RoomDto> rooms = chessRoomsService.findAllRooms();
			final Map<String, Object> model = mapRooms(rooms);
			return Renderer.getInstance().render(model, STATIC_PATH);
		});
	}

	private Map<String, Object> mapRooms(final List<RoomDto> rooms) {
		final Map<String, Object> model = new HashMap<>();
		model.put(ROOMS_KEY, rooms);
		return model;
	}

	private void routePostMethod() {
		Spark.post(PATH, (request, response) -> {
			final String method = request.queryParams("method");
			final String roomName = request.queryParams(ROOM_NAME_OF_FORM);
			if (method.equals("delete")) {
				return responseToDeleteRoom(response, roomName);
			}
			return responseToEnterOrCreateRoom(response, roomName);
		});
	}

	private String responseToEnterOrCreateRoom(final Response response, final String roomName) throws SQLException {
		try {
			responseToEnterRoom(response, roomName);
		} catch (DaoNoneSelectedException e) {
			responseToCreateRoom(response, roomName);
		}
		return EMPTY;
	}

	private void responseToEnterRoom(final Response response, final String roomName) throws SQLException {
		final RoomDto roomDto = chessRoomsService.findRoomByRoomName(roomName);
		response.redirect(PATH + SLASH + roomDto.getId());
	}

	private void responseToCreateRoom(final Response response, final String roomName) throws SQLException {
		chessRoomsService.addRoomByRoomName(roomName);
		response.redirect(PATH);
	}

	private String responseToDeleteRoom(final Response response, final String roomName) throws SQLException {
		chessRoomsService.deleteRoomByRoomName(roomName);
		response.redirect(PATH);
		return EMPTY;
	}

	public static ChessRoomsController getInstance() {
		return ROOMS_CONTROLLER;
	}
}
