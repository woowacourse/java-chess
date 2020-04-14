package chess.dao;

import static chess.dao.Connector.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.Turn;
import chess.domain.chessboard.ChessBoard;
import jdbc.DataAccessException;

public class TurnDAO {

	public Turn find() {
		String query = "SELECT * FROM turn";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			try (ResultSet resultSet = pstmt.executeQuery()) {
				return createTurn(resultSet);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	private Turn createTurn(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			addTurn(true);
			return new Turn(true);
		}
		boolean isWhiteTurn = resultSet.getBoolean("isWhiteTurn");
		return new Turn(isWhiteTurn);
	}

	public void removeAll() {
		String query = "TRUNCATE turn";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void addTurn(boolean isWhiteTurn) {
		String query = "INSERT INTO turn VALUES (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setBoolean(1, isWhiteTurn);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void changeTurn(boolean isWhiteTurn) {
		removeAll();
		addTurn(isWhiteTurn);
	}
}
