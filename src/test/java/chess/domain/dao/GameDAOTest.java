package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dao.GameDAO;
import chess.dao.RoomDAO;
import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

public class GameDAOTest {
	int roomId;
	private GameDAO gameDAO = GameDAO.getInstance();
	private RoomDAO roomDAO = RoomDAO.getInstance();

	@BeforeEach
	void setUp() throws SQLException {
		roomId = roomDAO.findRoomIdByRoomName("hello world");
		roomDAO.removeRoomById(roomId);
		AutoIncrementTest.applyAutoIncrementToZero();
		roomDAO.addRoom("hello world", "WHITE");
		roomId = roomDAO.findRoomIdByRoomName("hello world");
		gameDAO.removeAllPiecesById(roomId);
	}

	@DisplayName("Pieces를 모두 가져오는지 테스트")
	@Test
	public void findAllPiecesByIdTest() throws SQLException {
		Pieces originPieces = new Pieces(Pieces.initPieces());
		gameDAO.addAllPiecesById(roomId, originPieces);

		Map<Position, Piece> pieces = gameDAO.findAllPiecesById(roomId);

		assertThat(pieces.size()).isEqualTo(64);
	}

	@DisplayName("Pieces를 모두 삭제하는지 테스트")
	@Test
	public void removeAllPiecesByIdTest() throws SQLException {
		Map<Position, Piece> allPieces = gameDAO.findAllPiecesById(roomId);
		assertThat(allPieces.size()).isEqualTo(0);
	}

	@DisplayName("초기화된 Pieces를 모두 추가하는지 테스트")
	@Test
	public void addAllPiecesByIdTest() throws SQLException {
		Pieces originPieces = new Pieces(Pieces.initPieces());
		gameDAO.addAllPiecesById(roomId, originPieces);
		Map<Position, Piece> pieces = gameDAO.findAllPiecesById(roomId);

		assertThat(pieces.size()).isEqualTo(64);
	}

	@DisplayName("Position을 가지고 Piece를 찾는 테스트")
	@Test
	void findPieceByPositionTest() throws SQLException {
		Pieces originPieces = new Pieces(Pieces.initPieces());
		gameDAO.addAllPiecesById(roomId, originPieces);
		String position = "a2";

		Piece piece = gameDAO.findPieceByPosition(position);
		assertThat(piece.isPawn()).isTrue();
	}

	@DisplayName("체스 말을 다른 Position으로 update하는 테스트")
	@Test
	void updatePieceByPositionTest() throws SQLException {
		Pieces originPieces = new Pieces(new HashMap<>());
		originPieces.addPiece(Position.of("a2"), new Pawn(Color.WHITE, "p"));
		gameDAO.addAllPiecesById(roomId, originPieces);

		String currentPosition = "a2";
		String nextPosition = "a4";

		gameDAO.updatePieceByPosition(currentPosition, nextPosition);
		Piece piece = gameDAO.findPieceByPosition(nextPosition);
		assertThat(piece.isPawn()).isTrue();
	}

	@DisplayName("체스 말을 삭제하는 테스트")
	@Test
	void deletePieceByPositionTest() throws SQLException {
		Pieces originPieces = new Pieces(new HashMap<>());
		originPieces.addPiece(Position.of("a2"), new Pawn(Color.WHITE, "p"));
		gameDAO.addAllPiecesById(roomId, originPieces);

		String position = "a2";

		gameDAO.deletePieceByPosition(position);
		Piece piece = gameDAO.findPieceByPosition(position);
		assertThat(piece.isBlank()).isTrue();
	}

	@DisplayName("체스 말을 추가하는 테스트")
	@Test
	void addPieceByPositionTest() throws SQLException {
		Piece pawn = new Pawn(Color.WHITE, "p");
		Position position = Position.of("a3");

		gameDAO.addPieceByPosition(roomId, position, pawn);
		Piece piece = gameDAO.findPieceByPosition(position.getPosition());
		assertThat(piece.isPawn()).isTrue();
	}
}