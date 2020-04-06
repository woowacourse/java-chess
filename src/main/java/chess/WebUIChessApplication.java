package chess;

import dao.Room;
import dao.RoomDao;
import dao.exceptions.DaoNoneSelectedException;
import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.pieces.Piece;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.state.exceptions.StateException;
import spark.Response;
import view.Announcement;
import view.board.Board;
import domain.state.Ended;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.BoardToTable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebUIChessApplication {
	private static State state;
	private static Map<String, State> states;
	private static Announcement announcement;

	public static void main(String[] args) {
		Spark.port(8080);
		Spark.staticFiles.location("/statics");
		state = initState();
		announcement = Announcement.ofFirst();

		Spark.get("/chess/rooms", (request, response) -> {
			final RoomDao roomDao = new RoomDao();
			final List<Room> rooms = roomDao.findAllRooms();
			final Map<String, Object> map = new HashMap<>();
			map.put("rooms", rooms);
			return render(map, "/rooms.html");
		});

		Spark.post("/chess/rooms", (request, response) -> {
			final String requestType = request.queryParams("selection");
			final String roomName = request.queryParams("room_name");

			if (requestType.equals("enter_or_create")) {
				return responseToEnterOrCreateRoom(response, roomName);
			}
			if (requestType.equals("delete")) {
				return responseToDeleteRoom(response, roomName);
			}
			return "";
		});

		Spark.get("/chess/rooms/:id", (request, response) -> {
			final Map<String, Object> map = new HashMap<>();
			map.put("table", createTableHtmlFromState());
			map.put("announcement", announcement.getString());
			return render(map, "/chess.html");
		});

		Spark.post("/chess/rooms/:id", (request, response) -> {
			try {
				state = state.pushCommend(request.queryParams("commend"));
				announcement = createAnnouncement();
				response.redirect("/chess/rooms/" + request.params(":id"));
			} catch (CommandTypeException
					| MoveCommandTokensException
					| CanNotMoveException
					| CanNotAttackException
					| CanNotReachException
					| StateException e) {
				announcement = Announcement.of(e.getMessage());
				response.redirect("/chess/rooms/" + request.params(":id"));
			}
			return "";
		});
	}

	private static String responseToEnterOrCreateRoom(final Response response, final String roomName) throws SQLException {
		final RoomDao roomDao = new RoomDao();
		try {
			final Room room = roomDao.findRoomByRoomName(roomName);
			response.redirect("/chess/rooms/" + room.getId());
		} catch (DaoNoneSelectedException e) {
			final int resultNum = roomDao.addRoomByRoomName(roomName);
			response.redirect("/chess/rooms");
		}
		return "";
	}

	private static String responseToDeleteRoom(final Response response, final String roomName) throws SQLException {
		final RoomDao roomDao = new RoomDao();
		final int resultNum = roomDao.deleteRoomByRoomName(roomName);
		response.redirect("/chess/rooms");
		return "";
	}

	private static String createTableHtmlFromState() {
		final Set<Piece> pieces = state.getSet();
		final List<List<String>> board = Board.of(pieces).getLists();
		return BoardToTable.of(board).getTableHtml();
	}

	private static Ended initState() {
		return new Ended(new Pieces(new StartPieces().getInstance()));
	}

	private static Announcement createAnnouncement() {
		if (state.isReported()) {
			return Announcement.ofStatus(state.getPieces());
		}
		if (state.isEnded()) {
			return Announcement.ofEnd();
		}
		return Announcement.ofPlaying();
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
