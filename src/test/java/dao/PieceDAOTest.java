package dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Column;
import chess.board.Location;
import chess.board.Row;
import chess.piece.King;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.team.Team;

class PieceDAOTest {

	private RoomDAO roomDAO;
	private PieceDAO pieceDAO;

	@BeforeEach
	void setUp() throws SQLException {
		pieceDAO = new PieceDAO();
		roomDAO = new RoomDAO();
		roomDAO.createRoom(1000);
	}

	@AfterEach
	void tearDown() throws SQLException {
		roomDAO.delete(String.valueOf(1000L));
	}

	@DisplayName("Piece의 삽입 및 전체 삭제 테스트")
	@Test
	void addAllPiece() throws SQLException {
		Location location = Location.of(Column.A, Row.ONE);

		Map<Location, Piece> pieces = new HashMap<>();
		pieces.put(Location.of(Column.A, Row.ONE), Pawn.of(Team.WHITE));
		pieces.put(Location.of(Column.B, Row.ONE), Pawn.of(Team.WHITE));
		pieces.put(Location.of(Column.C, Row.ONE), Pawn.of(Team.WHITE));

		pieceDAO.addAllPiece(1000L, pieces);

		Map<Location, Piece> actual = pieceDAO.findAll(1000L);
		assertThat(actual).isEqualTo(pieces);

	}

	@DisplayName("피스의 Location 업데이트 확인 후 삭제")
	@Test
	void updateLocation() throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();

		Location givenLocation = Location.of(Column.A, Row.ONE);
		Location updateLocation = Location.of(Column.A, Row.TWO);

		Map<Location, Piece> pieces = new HashMap<>();
		pieces.put(givenLocation, King.of(Team.BLACK));
		pieceDAO.addAllPiece(1000L, pieces);

		pieceDAO.updateLocation(1000L, givenLocation, updateLocation);
		Piece actual = pieceDAO.findPiece(1000L, updateLocation);

		assertThat(actual).isEqualTo(King.of(Team.BLACK));
	}

	@DisplayName("피스의 Delete 테스트")
	@Test
	void delete() throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();

		Location givenLocation = Location.of(Column.A, Row.ONE);
		HashMap<Location, Piece> expect = new HashMap<>();

		Map<Location, Piece> pieces = new HashMap<>();
		pieces.put(givenLocation, King.of(Team.BLACK));
		pieceDAO.addAllPiece(1000L, pieces);

		pieceDAO.deletePiece(1000L, givenLocation);
		Map<Location, Piece> actual = pieceDAO.findAll(1000L);

		assertThat(actual).isEqualTo(expect);
	}
}