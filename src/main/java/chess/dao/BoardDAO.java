package chess.dao;

import static chess.dao.DBConnector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.board.Board;
import chess.domain.piece.Name;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

public class BoardDAO {
	public void initialize(String gameId, List<Piece> board) {
		String query = "INSERT INTO board VALUES (?, ?, ?)";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			for (Piece piece : board) {
				pstmt.setString(1, gameId);
				pstmt.setString(2, piece.getPosition().getName());
				pstmt.setString(3, piece.getSymbol());
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void addPiece(String gameId, Piece piece) {
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

	public Piece findPieceBy(String gameId, Position position) {
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

	public Board findBoardBy(String gameId) {
		List<Piece> board = new ArrayList<>();
		String query = "SELECT * FROM board WHERE game_id = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String symbol = rs.getString("symbol");
				Position position = Position.of(rs.getString("position"));
				board.add(PieceFactory.of(symbol).create(position));
			}
			pstmt.close();
			closeConnection(con);

			return Board.of(board);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void update(String gameId, Position from, Position to) {
		String query = "UPDATE board SET symbol = ? WHERE game_id = ? AND position = ?";
		Piece source = findPieceBy(gameId, from);
		Piece target = findPieceBy(gameId, to);
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, source.getSymbol());
			pstmt.setString(2, gameId);
			pstmt.setString(3, target.getPosition().getName());
			pstmt.addBatch();
			pstmt.clearParameters();

			pstmt.setString(1, Name.EMPTY.getBlackSymbol());
			pstmt.setString(2, gameId);
			pstmt.setString(3, source.getPosition().getName());
			pstmt.addBatch();
			pstmt.executeBatch();

			pstmt.close();
			closeConnection(con);
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