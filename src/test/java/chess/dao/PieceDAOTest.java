package chess.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.database.DatabaseConnection;
import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PieceDAOTest {
	private Connection connection;
	private PieceDAO pieceDAO;

	@BeforeEach
	void setUp() throws Exception {
		connection = DatabaseConnection.getConnection();
		pieceDAO = PieceDAO.getInstance(connection);
	}

	@Test
	void addAllPieces() throws Exception {
		List<Piece> pieces = new ArrayList<>(Arrays.asList(
				Rook.valueOf(Player.WHITE, Position.getPosition(1, 2)),
				Pawn.valueOf(Player.BLACK, Position.getPosition(2,3))));

	}
}
