package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.AutoIncrementTest;
import chess.dao.GameDAO;
import chess.dao.RoomDAO;
import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class GameServiceTest {
	private GameService gameService = GameService.getInstance();
	private GameDAO gameDAO = GameDAO.getInstance();
	private RoomDAO roomDAO = RoomDAO.getInstance();
	private int roomId;

	@BeforeEach
	void setUp() throws SQLException {
		roomId = roomDAO.findRoomIdByRoomName("hello world");
		roomDAO.removeRoomById(roomId);
		AutoIncrementTest.applyAutoIncrementToZero();
		roomDAO.addRoom("hello world", "WHITE");
		roomDAO.findRoomIdByRoomName("hello world");
		roomId = roomDAO.findRoomIdByRoomName("hello world");
	}

	@DisplayName("체스 말들을 초기화 하고 해당 체스 말들의 갯수가 64개인지 테스트")
	@Test
	void initializeTest() throws SQLException {
		gameService.initialize(roomId);

		Map<Position, Piece> pieces = gameDAO.findAllPiecesById(roomId);
		assertThat(pieces.size()).isEqualTo(64);
	}

	@DisplayName("체스 말을 움직이고나서 움직이기 전 체스 말과 같은지 테스트")
	@Test
	void movePieceTest() throws SQLException {
		String source = "a2";
		String target = "a4";
		gameService.initialize(roomId);
		gameService.movePiece(roomId, source, target);

		assertThat(gameDAO.findPieceByPosition(Position.of(target).getPosition()).isPawn()).isTrue();
	}

	@DisplayName("각 팀의 스코어를 리턴해주는 메서드 테스트")
	@Test
	void getScoreTest() throws SQLException {
		Color color = Color.WHITE;
		gameService.initialize(roomId);
		double score = gameService.getScore(roomId, color);

		assertThat(score).isEqualTo(38D);
	}
}
