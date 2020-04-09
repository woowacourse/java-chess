package controller;

import dao.exceptions.DaoNoneSelectedException;
import dto.RoomDto;
import service.RoomsService;
import spark.ModelAndView;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessRoomsController {
	private static final String PATH = "/chess/rooms";
	private static final String STATIC_PATH = "/rooms.html";
	private static final String EMPTY = "";
	private static final String SLASH = "/";
	private static final String ROOM_NAME_OF_FORM = "room_name";
	private static final String ROOMS_KEY = "rooms";
	private static final ChessRoomsController ROOMS_CONTROLLER;

	private final HandlebarsTemplateEngine handlebarsTemplateEngine;
	private final RoomsService roomsService;

	static {
		ROOMS_CONTROLLER = new ChessRoomsController(new HandlebarsTemplateEngine(), new RoomsService());
	}

	private ChessRoomsController(final HandlebarsTemplateEngine handlebarsTemplateEngine,
								 final RoomsService roomsService) {
		this.handlebarsTemplateEngine = handlebarsTemplateEngine;
		this.roomsService = roomsService;
	}

	public void run() {
		routeGetMethod();
		routePostMethod();
		routeDeleteMethod();
	}

	private void routeGetMethod() {
		Spark.get(PATH, (request, response) -> {
			final List<RoomDto> rooms = roomsService.findAllRooms();
			final Map<String, Object> model = mapRooms(rooms);
			return render(model, STATIC_PATH);
		});
	}

	private Map<String, Object> mapRooms(final List<RoomDto> rooms) {
		final Map<String, Object> model = new HashMap<>();
		model.put(ROOMS_KEY, rooms);
		return model;
	}

	private void routePostMethod() {
		Spark.post(PATH, (request, response) -> {
			final String roomName = request.queryParams(ROOM_NAME_OF_FORM);
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
		final RoomDto roomDto = roomsService.findRoomByRoomName(roomName);
		response.redirect(PATH + SLASH + roomDto.getId());
	}

	private void responseToCreateRoom(final Response response, final String roomName) throws SQLException {
		roomsService.addRoomByRoomName(roomName);
		response.redirect(PATH);
	}

	private void routeDeleteMethod() {
		Spark.delete(PATH, (request, response) -> {
			final String roomName = request.queryParams(ROOM_NAME_OF_FORM);
			return responseToDeleteRoom(response, roomName);
		});
	}

	private String responseToDeleteRoom(final Response response, final String roomName) throws SQLException {
		roomsService.deleteRoomByRoomName(roomName);
		response.redirect(PATH);
		return EMPTY;
	}

	private String render(final Map<String, Object> model, final String templatePath) {
		return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
	}

	public static ChessRoomsController getInstance() {
		return ROOMS_CONTROLLER;
	}
}
