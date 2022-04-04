package chess.repository.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConnectionManager {

	private static final String NOT_FOUND_PRIMARY_KEY = "기본키를 찾을 수 없습니다.";
	private static final String NOT_EXIST = "존재하지 않는 값입니다..";
	private static final String DUPLICATE_UNIQUE = "이미 존재하는 이름입니다.";

	private static final String URL = "jdbc:mysql://localhost:3306/chess";
	private static final String USER = "user";
	private static final String PASSWORD = "password";

	private final PreparedStatement statement;
	private ResultSet resultSet;

	private ConnectionManager(PreparedStatement statement) {
		this.statement = statement;
	}

	public static ConnectionManager createQuery(String sql) {
		PreparedStatement statement = null;
		try {
			statement = getConnection()
				.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ConnectionManager(statement);
	}

	public ConnectionManager setParameter(int index, String parameter) {
		try {
			Objects.requireNonNull(statement)
				.setString(index, parameter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ConnectionManager setParameter(int index, int parameter) {
		try {
			Objects.requireNonNull(statement)
				.setInt(index, parameter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ConnectionManager executeUpdate() {
		try {
			Objects.requireNonNull(this.statement).executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new IllegalStateException(DUPLICATE_UNIQUE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ConnectionManager executeQuery() {
		try {
			resultSet = Objects.requireNonNull(this.statement).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	public int getGeneratedKey() {
		try {
			resultSet = Objects.requireNonNull(this.statement).getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
			throw new IllegalArgumentException(NOT_FOUND_PRIMARY_KEY);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String getResult(String parameter) {
		Objects.requireNonNull(resultSet);
		try {
			if (resultSet.next()) {
				return resultSet.getString(parameter);
			}
			throw new IllegalArgumentException(NOT_EXIST);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public List<String> getResultList(String parameter) {
		Objects.requireNonNull(resultSet);
		List<String> names = new ArrayList<>();
		try {
			while (resultSet.next()) {
				names.add(resultSet.getString(parameter));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return names;
	}

	public Map<String, String> getResultMap(String key, String value) {
		Objects.requireNonNull(resultSet);
		Map<String, String> tiles = new HashMap<>();
		try {
			while (resultSet.next()) {
				tiles.put(resultSet.getString(key), resultSet.getString(value));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiles;
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection =  DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
