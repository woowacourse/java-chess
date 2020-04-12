package chess;

import controller.ChessRoomsController;
import controller.Renderer;
import dao.*;
import dao.exceptions.DaoNoneSelectedException;
import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.state.exceptions.StateException;
import dto.StatusRecordWithRoomNameDto;
import service.RoomService;
import spark.Spark;
import view.Announcement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessApplication {
	private static final int PORT = 8080;
	private static final String STATIC_FILES = "/statics";
	private static final ChessRoomsController ROOM_CONTROLLER = ChessRoomsController.getInstance();

	public static void main(String[] args) {
		Spark.port(PORT);
		Spark.staticFiles.location(STATIC_FILES);

		ROOM_CONTROLLER.run();

		Spark.get("/chess/rooms/:id", (request, response) -> {
			final int roomId = Integer.parseInt(request.params(":id"));
			try {
				return response(roomId);
			} catch (DaoNoneSelectedException e) {
				return responseWhenHasNoState(roomId);
			}
		});

		Spark.post("/chess/rooms/:id", (request, response) -> {
			final int roomId = Integer.parseInt(request.params(":id"));
			final String commend = request.queryParams("commend");
			RoomService roomService = RoomService.getInstance();

			try {
				roomService.updateRoom(roomId, commend);
			} catch (CommandTypeException
					| MoveCommandTokensException
					| CanNotMoveException
					| CanNotAttackException
					| CanNotReachException
					| StateException e) {
				roomService.saveAnnouncementMessage(roomId, Announcement.of(e.getMessage()).getString());
			}
			response.redirect("/chess/rooms/" + request.params(":id"));
			return "";
		});

		Spark.get("/chess/statistics", (request, response) -> {
			final StatusRecordWithRoomNameDao statusRecordWithRoomNameDao = new StatusRecordWithRoomNameDao();
			final List<StatusRecordWithRoomNameDto> statusRecordWithRoomNames
					= statusRecordWithRoomNameDao.findStatusRecordWithRoomName();

			final Map<String, Object> map = new HashMap<>();
			map.put("status_record_with_room_names", statusRecordWithRoomNames);
			return Renderer.getInstance().render(map, "/statistics.html");
		});
	}


	private static String response(final int roomId) throws SQLException {
		final RoomService roomService = RoomService.getInstance();
		final String boardHtml = RoomService.getInstance().loadBoardHtml(roomId);
		final String announcementMessage = roomService.loadAnnouncementMessage(roomId);

		final Map<String, Object> map = new HashMap<>();
		map.put("table", boardHtml);
		map.put("announcement", announcementMessage);

		return Renderer.getInstance().render(map, "/chess.html");
	}

	private static String responseWhenHasNoState(final int roomId) throws SQLException {
		final RoomService roomService = RoomService.getInstance();
		roomService.saveNewState(roomId);
		roomService.saveNewPieces(roomId);
		roomService.saveNewAnnouncementMessage(roomId);

		final String boardHtml = roomService.loadBoardHtml(roomId);
		final String announcementMessage = roomService.loadAnnouncementMessage(roomId);

		final Map<String, Object> map = new HashMap<>();
		map.put("table", boardHtml);
		map.put("announcement", announcementMessage);
		return Renderer.getInstance().render(map, "/chess.html");
	}
}
