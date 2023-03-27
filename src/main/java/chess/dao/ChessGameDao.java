package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import chess.dto.MoveDto;

public class ChessGameDao {

	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
		} catch (final SQLException e) {
			System.err.println("DB 연결 오류:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkConnection() {
		try (final Connection connection = getConnection()) {
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean isLastGameExists() {
		final String query = "SELECT COUNT(*) as cnt FROM move";
		try (
			final Connection connection = getConnection();
			final PreparedStatement statement = connection.prepareStatement(query)
		) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("cnt") > 0) {
					return true;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public void saveMove(final MoveDto moveDto) {
		final String query = "INSERT INTO move(source_column, source_row, target_column, target_row) VALUES (?, ?, ?, ?)";
		try (
			final Connection connection = getConnection();
			final PreparedStatement statement = connection.prepareStatement(query)
		) {
			statement.setInt(1, moveDto.getSourceColumn());
			statement.setInt(2, moveDto.getSourceRow());
			statement.setInt(3, moveDto.getTargetColumn());
			statement.setInt(4, moveDto.getTargetRow());
			statement.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<MoveDto> loadMoves() {
		final String query = "SELECT * FROM move";
		try (
			final Connection connection = getConnection();
			final PreparedStatement statement = connection.prepareStatement(query)
		) {
			final ResultSet resultSet = statement.executeQuery();
			final List<MoveDto> moves = new ArrayList<>();
			while (resultSet.next()) {
				int sourceColumn = resultSet.getInt("source_column");
				int sourceRow = resultSet.getInt("source_row");
				int targetColumn = resultSet.getInt("target_column");
				int targetRow = resultSet.getInt("target_row");
				moves.add(new MoveDto(sourceColumn, sourceRow, targetColumn, targetRow));
			}
			return moves;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteMoves() {
		final String query = "DELETE FROM move";
		try (
			final Connection connection = getConnection();
			final PreparedStatement statement = connection.prepareStatement(query)
		) {
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
