package chess;

import dao.*;
import dao.exceptions.DaoNoneSelectedException;
import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.pieces.Piece;
import domain.pieces.PieceType;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Coordinate;
import domain.state.State;
import domain.state.StateType;
import domain.state.exceptions.StateException;
import domain.team.Team;
import dto.*;
import spark.ModelAndView;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.Announcement;
import view.BoardToTable;
import view.board.Board;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class WebUIChessApplication {
	public static HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

	public static void main(String[] args) {
		Spark.port(8080);
		Spark.staticFiles.location("/statics");

		Spark.get("/chess/rooms", (request, response) -> {
			final RoomDao roomDao = new RoomDao();
			final List<RoomDto> roomDtos = roomDao.findAllRooms();
			final Map<String, Object> map = new HashMap<>();
			map.put("rooms", roomDtos);
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
				return responseWhenHasState(roomId);
			} catch (DaoNoneSelectedException e) {
				return responseWhenHasNoState(roomId);
			}

		});

		Spark.post("/chess/rooms/:id", (request, response) -> {
			final int roomId = Integer.parseInt(request.params(":id"));
			final dao.PieceDao pieceDao = new PieceDao();
			final dao.StateDao stateDao = new StateDao();
			final AnnouncementDao announcementDao = new AnnouncementDao();
			final Set<Piece> domainPieces = pieceDao.findPiecesByRoomId(roomId).stream()
					.map(pieceDto -> PieceType.getFactoryOfName(pieceDto.getPieceType()).apply(
							Team.of(pieceDto.getTeam()), Coordinate.of(pieceDto.getCoordinate())))
					.collect(Collectors.toSet());
			final Pieces pieces = new Pieces(domainPieces);
			final StateDto daoStateDto = stateDao.findStateByRoomId(roomId);
			final State domainState = StateType.getFactory(daoStateDto.getState()).apply(pieces);
			try {
				final State after = domainState.pushCommend(request.queryParams("commend"));
				stateDao.setStateByRoomId(roomId, after.getStateName());
				pieceDao.deletePiece(roomId);
				for (final Piece piece : after.getSet()) {
					pieceDao.addPiece(piece.getPieceTypeName(), piece.getTeamName(), piece.getCoordinateRepresentation()
							, roomId);
				}
				announcementDao.setAnnouncementByRoomId(roomId, createRightAnnouncement(after));
				if (after.isEnded()) {
					final StatusRecordDao statusRecordDao = new StatusRecordDao();
					statusRecordDao.addStatusRecord(Announcement.ofStatus(after.getPieces()).getString(), roomId);
				}
			} catch (CommandTypeException
					| MoveCommandTokensException
					| CanNotMoveException
					| CanNotAttackException
					| CanNotReachException
					| StateException e) {
				announcementDao.setAnnouncementByRoomId(roomId, Announcement.of(e.getMessage()).getString());
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
			return render(map, "/statistics.html");
		});
	}

	private static String responseToEnterOrCreateRoom(final Response response, final String roomName) throws SQLException {
		final RoomDao roomDao = new RoomDao();
		try {
			final RoomDto roomDto = roomDao.findRoomByRoomName(roomName);
			response.redirect("/chess/rooms/" + roomDto.getId());
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

	private static String responseWhenHasState(final int roomId) throws SQLException {
		final StateDao stateDao = new StateDao();
		final dao.PieceDao pieceDao = new dao.PieceDao();
		final AnnouncementDao announcementDao = new AnnouncementDao();

		final StateDto daoStateDto = stateDao.findStateByRoomId(roomId);
		final List<PieceDto> daoPieceDtos = pieceDao.findPiecesByRoomId(roomId);

		final Set<Piece> domainPieces = mapDaoPiecesToDomainPieces(daoPieceDtos);
		final State domainState = StateType.getFactory(daoStateDto.getState()).apply(
				new Pieces(domainPieces));

		final AnnouncementDto daoAnnouncement = announcementDao.findAnnouncementByRoomId(roomId);

		final Map<String, Object> map = new HashMap<>();
		map.put("table", createTableHtmlWhenHasState(domainPieces));
		map.put("announcement", daoAnnouncement.getMessage());
		return render(map, "/chess.html");
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

	private static Set<Piece> mapDaoPiecesToDomainPieces(final List<PieceDto> daoPieceDtos) {
		return daoPieceDtos.stream()
				.map(daoPieceDto -> PieceType.getFactoryOfName(daoPieceDto.getPieceType()).apply(
						Team.of(daoPieceDto.getTeam()), Coordinate.of(daoPieceDto.getCoordinate())))
				.collect(Collectors.toSet());
	}

	private static String responseWhenHasNoState(final int roomId) throws SQLException {
		final StateDao stateDao = new StateDao();
		final dao.PieceDao pieceDao = new dao.PieceDao();
		final AnnouncementDao announcementDao = new AnnouncementDao();

		final Set<domain.pieces.Piece> pieces = new StartPieces().getInstance();
		pieceDao.deletePiece(roomId);
		for (Piece piece : pieces) {
			pieceDao.addPiece(piece.getPieceTypeName(), piece.getTeamName(),
					piece.getCoordinateRepresentation(), roomId);
		}
		stateDao.addState("ended", roomId);
		announcementDao.addAnnouncement("게임을 시작하려면 start를 입력해주세요.", roomId);

		final Map<String, Object> map = new HashMap<>();
		map.put("table", createTableHtmlWhenHasNoState(roomId));
		map.put("announcement", announcementDao.findAnnouncementByRoomId(roomId).getMessage());
		return render(map, "/chess.html");
	}

	private static String createTableHtmlWhenHasState(final Set<Piece> pieces) {
		return createTableHtml(pieces);
	}

	private static String createTableHtmlWhenHasNoState(int roomId) throws SQLException {
		final dao.PieceDao pieceDao = new PieceDao();
		final Set<Piece> pieces = pieceDao.findPiecesByRoomId(roomId).stream()
				.map(pieceDto -> PieceType.getFactoryOfName(pieceDto.getPieceType()).apply(
						Team.of(pieceDto.getTeam()), Coordinate.of(pieceDto.getCoordinate())))
				.collect(Collectors.toSet());
		return createTableHtml(pieces);
	}

	private static String createTableHtml(final Set<Piece> pieces) {
		final List<List<String>> board = Board.of(pieces).getLists();
		return BoardToTable.of(board).getTableHtml();
	}

	public static String render(final Map<String, Object> model, final String templatePath) {
		return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
	}
}
