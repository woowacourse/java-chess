package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessCommand;
import chess.domain.position.Position;
import chess.web.dao.ChessHistoryDao;
import chess.web.dao.InMemoryChessHistoryDao;

class ChessServiceTest {

	private ChessHistoryDao chessHistoryDao;

	@BeforeEach
	void setUp() {
		chessHistoryDao = new InMemoryChessHistoryDao();
	}

	@Test
	void ChessService_ChessHistoryDao_GenerateInstance() {
		assertThat(new ChessService(chessHistoryDao)).isInstanceOf(ChessService.class);
	}

	@Test
	void loadChessGame_ChessGame_ReturnChessGame() throws SQLException {
		chessHistoryDao.addHistory("b2", "b4");
		chessHistoryDao.addHistory("b7", "b5");
		ChessService chessService = new ChessService(chessHistoryDao);

		ChessBoard expected = new ChessBoard(ChessBoardInitializer.create());
		expected.moveChessPiece(Position.of("b2"), Position.of("b4"));
		expected.moveChessPiece(Position.of("b7"), Position.of("b5"));
		assertThat(chessService.loadChessGame()).extracting("chessBoard").isEqualTo(expected);
	}

	@Test
	void moveChessPiece_SourcePositionAndTargetPosition_moveChessPiece() throws SQLException {
		ChessService chessService = new ChessService(chessHistoryDao);
		chessService.moveChessPiece("b2", "b4");

		assertThat(chessHistoryDao.findAll()).contains(ChessCommand.of(Arrays.asList("move", "b2", "b4")));
	}

	@Test
	void deleteChessGame_RemoveAllChessHistory() throws SQLException {
		chessHistoryDao.addHistory("b2", "b4");
		chessHistoryDao.addHistory("b7", "b5");
		ChessService chessService = new ChessService(chessHistoryDao);
		chessService.deleteChessGame();

		assertThat(chessHistoryDao.findAll().isEmpty()).isTrue();
	}

	@AfterEach
	void tearDown() {
		try {
			chessHistoryDao.deleteAll();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}