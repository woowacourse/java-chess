package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.dto.PieceNameConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardDaoTest {
	private ChessBoardDao chessBoardDao;

	@BeforeEach
	void setUp() {
		chessBoardDao = new ChessBoardDao();
	}

	@Test
	void connect() {
		Connection connection = chessBoardDao.getConnection();
		assertThat(connection).isNotNull();

	}

	@Test
	void delete() {
		chessBoardDao.deleteByGameId(0);
	}

	@Test
	void crud() {
		Piece piece = PieceNameConverter.toPiece("\u265A", 1, 1);
		ChessBoard chessBoard = new ChessBoard(Collections.singletonList(piece));
		chessBoardDao.deleteByGameId(0);
		chessBoardDao.addPieces(chessBoard, 0);
		assertThat(chessBoardDao.findByGameId(0).getPieces().get(0)).isEqualToComparingFieldByField(piece);
	}

	@Test
	void findWithException() {
		chessBoardDao.deleteByGameId(0);
		assertThatThrownBy(() -> chessBoardDao.findByGameId(0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id에 해당하는 정보가 없습니다.");
	}
}