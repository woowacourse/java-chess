package controller;

import dao.exceptions.DaoNoneSelectedException;
import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.coordinate.exceptions.CoordinateException;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.state.exceptions.StateException;
import service.ChessRoomService;
import spark.Spark;
import view.Announcement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessRoomController {
	private static final ChessRoomController CHESS_ROOM_CONTROLLER;
	private static final String PATH = "/chess/rooms/:id";
	private static final String STATIC_PATH = "/chess.html";
	private static final String SLASH = "/";
	private static final String EMPTY = "";
	private static final String ID_PARAM = ":id";
	private static final String COMMEND_QUERYPARAM = "commend";
	private static final String TABLE_KEY = "table";
	private static final String ANNOUNCEMENT_KEY = "announcement";

	static {
		CHESS_ROOM_CONTROLLER = new ChessRoomController(ChessRoomService.getInstance());
	}

	private final ChessRoomService chessRoomService;

	private ChessRoomController(final ChessRoomService chessRoomService) {
		this.chessRoomService = chessRoomService;
	}

	public static ChessRoomController getInstance() {
		return CHESS_ROOM_CONTROLLER;
	}

	public void run() {
		routeGetMethod();
		routePostMethod();
	}

	private void routeGetMethod() {
		Spark.get(PATH, (request, response) -> {
			final int roomId = Integer.parseInt(request.params(ID_PARAM));
			try {
				return response(roomId);
			} catch (DaoNoneSelectedException e) {
				initRoom(roomId);
				return response(roomId);
			}
		});
	}

	private String response(final int roomId) throws SQLException {
		final String boardHtml = chessRoomService.loadBoardHtml(roomId);
		final String announcementMessage = chessRoomService.loadAnnouncementMessage(roomId);

		final Map<String, Object> map = new HashMap<>();
		map.put(TABLE_KEY, boardHtml);
		map.put(ANNOUNCEMENT_KEY, announcementMessage);

		return Renderer.getInstance().render(map, STATIC_PATH);
	}

	private void initRoom(final int roomId) throws SQLException {
		chessRoomService.saveNewState(roomId);
		chessRoomService.saveNewPieces(roomId);
		chessRoomService.saveNewAnnouncementMessage(roomId);
	}

	private void routePostMethod() {
		Spark.post(PATH, (request, response) -> {
			final int roomId = Integer.parseInt(request.params(ID_PARAM));
			final String commend = request.queryParams(COMMEND_QUERYPARAM);

			try {
				chessRoomService.updateRoom(roomId, commend);
			} catch (CommandTypeException
					| MoveCommandTokensException
					| CanNotMoveException
					| CanNotAttackException
					| CanNotReachException
					| StateException
					| CoordinateException e) {
				chessRoomService.saveAnnouncementMessage(roomId, Announcement.of(e.getMessage()).getString());
			}
			response.redirect(ChessRoomsController.PATH + SLASH + request.params(ID_PARAM));
			return EMPTY;
		});
	}
}
