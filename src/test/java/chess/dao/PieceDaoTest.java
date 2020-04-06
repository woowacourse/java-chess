package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.piece.king.King;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.Position;

class PieceDaoTest {
	private PieceDao pieceDao = new PieceDao(new RepositoryConnector());

	@Test
	void crud() throws SQLException {
		PieceDao pieceDao = new PieceDao(new RepositoryConnector());
		pieceDao.addPiece(1, Pawn.of(Team.WHITE, Position.of("a2")));
		pieceDao.addPiece(2, Pawn.of(Team.BLACK, Position.of("b2")));
		pieceDao.updateByPositionId(1, King.of(Team.WHITE, Position.of("a2")));

		assertThat(pieceDao.findPieceByPositionId(1)).isInstanceOf(King.class);
	}

	@BeforeEach
	void afterAll() throws SQLException {
		pieceDao.deleteByPositionId(1);
		pieceDao.deleteByPositionId(2);
		pieceDao.deleteByPositionId(3);
	}
}