package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class PieceDaoTest {
	private PieceDao pieceDao;

	@BeforeEach
	void setUp() {
		pieceDao = new PieceDao();
	}

	@Test
	void connection() {
		Connection con = pieceDao.getConnection();
		assertNotNull(con);
	}

	@Test
	void insert() {
		Piece piece = new Pawn(Position.of("a1"), new BlackTeam());
		try {
			pieceDao.addPiece(piece);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Test
	void delete() {
		Piece piece = new Pawn(Position.of("a1"), new BlackTeam());
		try {
			pieceDao.deletePiece(piece);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Test
	void update() {
		Piece knight = new Knight(Position.of("c1"), new BlackTeam());
		Piece pawn = new Pawn(Position.of("c2"), new BlackTeam());
		Position targetPosition = Position.of("b3");
		try {
			pieceDao.addPiece(knight);
			pieceDao.addPiece(pawn);
			pieceDao.updatePiece(knight, targetPosition);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Test
	void deleteAll() {
		try {
			pieceDao.deleteAll();
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Test
	void readPieces() {
		try {
			ResultSet rs = pieceDao.readPieces();
			if (rs.next()) {
				System.out.println(rs.getObject(4));
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
