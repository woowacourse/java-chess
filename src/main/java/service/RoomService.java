package service;

import dao.PieceDao;
import dao.StateDao;
import domain.pieces.Piece;
import domain.pieces.PieceType;
import domain.pieces.Pieces;
import domain.point.Coordinate;
import domain.state.State;
import domain.state.StateType;
import domain.team.Team;
import dto.AnnouncementDto;
import dto.PieceDto;
import dto.StateDto;
import view.BoardToHtml;
import view.board.Board;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomService {
	private static final RoomService ROOM_SERVICE;

	static {
		ROOM_SERVICE = new RoomService(new StateDao(), new PieceDao());
	}

	private final StateDao stateDao;
	private final PieceDao pieceDao;

	private RoomService(final StateDao stateDao, final PieceDao pieceDao) {
		this.stateDao = stateDao;
		this.pieceDao = pieceDao;
	}

	public static RoomService getInstance() {
		return ROOM_SERVICE;
	}

	public String loadBoardHtml(final int roomId) throws SQLException {
		final StateDto StateDto = stateDao.findStateByRoomId(roomId);
		final List<PieceDto> PieceDtos = pieceDao.findPiecesByRoomId(roomId);

		final Set<Piece> pieces = createPieces(PieceDtos);
		final State state = createState(StateDto, pieces);

		return createBoardHtml(state);
	}

	private Set<Piece> createPieces(final List<PieceDto> pieceDtos) {
		return pieceDtos.stream()
				.map(daoPieceDto -> PieceType.getFactoryOfName(daoPieceDto.getPieceType()).apply(
						Team.of(daoPieceDto.getTeam()), Coordinate.of(daoPieceDto.getCoordinate())))
				.collect(Collectors.toSet());
	}

	private State createState(final StateDto stateDto, final Set<Piece> pieces) {
		return StateType.getFactory(stateDto.getState()).apply(
				new Pieces(pieces));
	}

	private String createBoardHtml(final State state) {
		final List<List<String>> board = Board.of(state.getSet()).getLists();
		return BoardToHtml.of(board).getHtml();
	}
}
