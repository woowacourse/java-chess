package dao;

import domain.pieces.Queen;
import domain.point.Coordinate;
import domain.team.Team;
import dto.PieceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
	private PieceDao pieceDao;

	@BeforeEach
	void setUp() {
		pieceDao = PieceDao.getInstance();
	}

	@Test
	void addPiece() throws SQLException {
		final domain.pieces.Piece piece = new Queen(Team.BLACK, Coordinate.of("a2"));
		final int resultNum = pieceDao.addPiece(piece.getPieceTypeName(),
				piece.getTeamName(), piece.getCoordinateRepresentation(), 1);
		assertThat(resultNum).isEqualTo(1);
	}

	@Test
	void findPiecesByRoomId() throws SQLException {
		final int roomId = 1;
		final List<PieceDto> pieceDtos = pieceDao.findPiecesByRoomId(roomId);
		assertThat(pieceDtos).isEqualTo(Arrays.asList(
				new PieceDto(1, "queen", "black", "a2", 1)));
	}
}