package chess.dao;

import static chess.dao.Connector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Position;
import chess.dto.ChessDTO;
import jdbc.DataAccessException;

public class ChessDAO {

	public List<ChessDTO> findAll() {
		String query = "SELECT * FROM board";
		try (Connection connection = getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();
			return createDTOS(resultSet);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	private List<ChessDTO> createDTOS(ResultSet resultSet) throws SQLException {
		List<ChessDTO> chessDTOS = new ArrayList<>();
		while (resultSet.next()) {
			String position = resultSet.getString("position");
			String name = resultSet.getString("name");
			chessDTOS.add(new ChessDTO(position, name));
		}
		return chessDTOS;
	}

	public void addPiece(ChessDTO chessDTO) {
		String query = "INSERT INTO board (position, name) VALUES (?, ?)";
		try (Connection connection = getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, chessDTO.getPosition());
			pstmt.setString(2, chessDTO.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void removeAll() {
		String query = "TRUNCATE board";
		try (Connection connection = getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void update(Position position, String name) {
		String query = "UPDATE board SET name = (?) WHERE position = (?)";
		try (Connection connection = getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, position.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
