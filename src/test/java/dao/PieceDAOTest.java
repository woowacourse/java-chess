package dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Column;
import chess.board.Location;
import chess.board.Row;
import chess.piece.King;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Rook;
import chess.team.Team;

class PieceDAOTest {

	@DisplayName("Piece 테이블의 생성 제거 테스트")
	@Test
	void addPiece() throws SQLException {
		Piece piece = new King(Team.BLACK);
		Location location = Location.of(Column.A, Row.ONE);
		PieceDAO pieceDAO = new PieceDAO();

		pieceDAO.addPiece(location, piece);

		Piece actual = pieceDAO.findPiece(location);
		assertThat(actual).isEqualTo(piece);

		pieceDAO.deletePiece(location);
	}

	@DisplayName("모든 피스들을 조회 후 모두 삭제")
	@Test
	void findAll() throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();
		Map<Location, Piece> pieces = new HashMap<>();
		pieces.put(Location.of(Column.A, Row.ONE), King.of(Team.BLACK));
		pieces.put(Location.of(Column.B, Row.TWO), Pawn.of(Team.BLACK));
		pieces.put(Location.of(Column.C, Row.THREE), Rook.of(Team.WHITE));

		pieceDAO.addPiece(Location.of(Column.A, Row.ONE), King.of(Team.BLACK));
		pieceDAO.addPiece(Location.of(Column.B, Row.TWO), Pawn.of(Team.BLACK));
		pieceDAO.addPiece(Location.of(Column.C, Row.THREE), Rook.of(Team.WHITE));

		Map<Location, Piece> actual = pieceDAO.findAll();
		assertThat(actual).isEqualTo(pieces);

		pieceDAO.deleteAll();
	}

	@Test
	void updateLocation() {
	}
}