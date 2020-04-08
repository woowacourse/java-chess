package chess;

import dao.*;
import dao.exceptions.DaoNoneSelectedException;
import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.pieces.Piece;
import domain.pieces.PieceType;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Coordinate;
import domain.state.StateType;
import domain.state.exceptions.StateException;
import domain.team.Team;
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
import java.util.*;
import java.util.stream.Collectors;

public class WebUIChessApplication {

	public static void main(String[] args) {
		Spark.port(8080);
		Spark.staticFiles.location("/statics");

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
			final int roomId = Integer.parseInt(request.params(":id"));
			try {
				responseWhenHasState(roomId);
			} catch (DaoNoneSelectedException e) {
				responseWhenHasNoState(roomId);
			}
			final AnnouncementDao announcementDao = new AnnouncementDao();
			final dao.Announcement announcement = announcementDao.findAnnouncementByRoomId(roomId);

			final Map<String, Object> map = new HashMap<>();
			map.put("table", createTableHtmlFromState(roomId));
			map.put("announcement", announcement.getMessage());
			return render(map, "/chess.html");
		});

		Spark.post("/chess/rooms/:id", (request, response) -> {
			final int roomId = Integer.parseInt(request.params(":id"));
			final dao.PieceDao pieceDao = new PieceDao();
			final dao.StateDao stateDao = new StateDao();
			final AnnouncementDao announcementDao = new AnnouncementDao();
			final Set<Piece> domainPieces = pieceDao.findPiecesByRoomId(roomId).stream()
					.map(piece -> PieceType.getFactoryOfName(piece.getPieceType()).apply(
							Team.of(piece.getTeam()), Coordinate.of(piece.getCoordinate())))
					.collect(Collectors.toSet());
			final Pieces pieces = new Pieces(domainPieces);
			final dao.State daoState = stateDao.findStateByRoomId(roomId);
			final State domainState = StateType.getFactory(daoState.getState()).apply(pieces);
			try {
				final State after = domainState.pushCommend(request.queryParams("commend"));
				stateDao.setStateByRoomId(roomId, after.getStateName());
				pieceDao.deletePiece(roomId);
				for (final Piece piece : after.getSet()) {
					pieceDao.addPiece(piece.getPieceTypeName(), piece.getTeamName(), piece.getCoordinateRepresentation()
							, roomId);
				}
				announcementDao.addAnnouncement(createRightAnnouncement(after), roomId);
			} catch (CommandTypeException
					| MoveCommandTokensException
					| CanNotMoveException
					| CanNotAttackException
					| CanNotReachException
					| StateException e) {
				announcementDao.addAnnouncement(Announcement.of(e.getMessage()).getString(), roomId);
			}
			response.redirect("/chess/rooms/" + request.params(":id"));
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

	private static void responseWhenHasState(final int roomId) throws SQLException {
		final StateDao stateDao = new StateDao();
		final dao.PieceDao pieceDao = new dao.PieceDao();
		final AnnouncementDao announcementDao = new AnnouncementDao();

		final dao.State daoState = stateDao.findStateByRoomId(roomId);
		final List<dao.Piece> daoPieces = pieceDao.findPiecesByRoomId(roomId);

		final Set<Piece> domainPieces = mapDaoPiecesToDomainPieces(daoPieces);
		final State domainState = StateType.getFactory(daoState.getState()).apply(
				new Pieces(domainPieces));
		announcementDao.addAnnouncement(createRightAnnouncement(domainState), roomId);
	}

	private static String createRightAnnouncement(domain.state.State domainState) {
		if (domainState.isPlaying()) {
			return Announcement.ofPlaying().getString();
		}
		if (domainState.isReported()) {
			return Announcement.ofStatus(domainState.getPieces()).getString();
		}
		return Announcement.ofEnd().getString();
	}

	private static Set<Piece> mapDaoPiecesToDomainPieces(final List<dao.Piece> daoPieces) {
		return daoPieces.stream()
				.map(daoPiece -> PieceType.getFactoryOfName(daoPiece.getPieceType()).apply(
						Team.of(daoPiece.getTeam()), Coordinate.of(daoPiece.getCoordinate())))
				.collect(Collectors.toSet());
	}

	private static void responseWhenHasNoState(final int roomId) throws SQLException {
		final StateDao stateDao = new StateDao();
		final dao.PieceDao pieceDao = new dao.PieceDao();
		final AnnouncementDao announcementDao = new AnnouncementDao();

		final Set<domain.pieces.Piece> pieces = new StartPieces().getInstance();
		final State domainState = new Ended(new Pieces(pieces));
		pieceDao.deletePiece(roomId);
		for (Piece piece : pieces) {
			pieceDao.addPiece(piece.getPieceTypeName(), piece.getTeamName(),
					piece.getCoordinateRepresentation(), roomId);
		}
		stateDao.addState("ended", roomId);
		announcementDao.addAnnouncement("게임을 시작하려면 start를 입력해주세요.", roomId);
	}

	private static String createTableHtmlFromState(int roomId) throws SQLException {
		final dao.PieceDao pieceDao = new PieceDao();
		final Set<Piece> pieces = pieceDao.findPiecesByRoomId(roomId).stream()
				.map(piece -> PieceType.getFactoryOfName(piece.getPieceType()).apply(
						Team.of(piece.getTeam()), Coordinate.of(piece.getCoordinate())))
				.collect(Collectors.toSet());
		final List<List<String>> board = Board.of(pieces).getLists();
		return BoardToTable.of(board).getTableHtml();
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
