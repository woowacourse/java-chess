package chess.dao;

import static chess.dao.DBConnector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

public class BoardDAO {
	private final String gameId;

	public BoardDAO(String gameId) {
		this.gameId = gameId;
	}

	public void addPiece(Piece piece) {
		String query = "INSERT INTO board VALUES (?, ?, ?)";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			pstmt.setString(2, piece.getPosition().getName());
			pstmt.setString(3, piece.getSymbol());
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Piece findPieceBy(Position position) {
		String query = "SELECT * FROM board WHERE game_id = ? AND position = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			pstmt.setString(2, position.getName());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Piece piece = PieceFactory.of(rs.getString("symbol")).create(position);
			pstmt.close();
			closeConnection(con);

			return piece;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void truncate() {
		String query = "TRUNCATE board";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}