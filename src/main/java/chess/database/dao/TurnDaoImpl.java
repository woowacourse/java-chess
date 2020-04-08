package chess.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.database.DataSource;
import chess.domain.piece.Color;

public class TurnDaoImpl implements TurnDao {
	private final DataSource dataSource;

	public TurnDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(String turn) {
		String query = "INSERT INTO turn VALUES (?)";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, turn);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	@Override
	public void update(String turn) {
		String query = "UPDATE turn SET current_turn = (?)";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, turn);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	@Override
	public Color getTurn() {
		String query = "SELECT * FROM turn";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);
			 ResultSet resultSet = preparedStatement.executeQuery()) {
			resultSet.next();
			return Color.of(resultSet.getString("current_turn"));
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}

	@Override
	public void deleteTurn() {
		String query = "DELETE FROM turn";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}
}
