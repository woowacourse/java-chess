package chess.dao;

import java.sql.SQLException;

public class GamesDto {
	private final RepositoryConnector connector;

	public GamesDto(RepositoryConnector connector) {
		this.connector = connector;
	}

	public int createGame(String firstUser, String secondUser) throws SQLException {
		String query = "insert into games(user1, user2) values (?, ?)";
		System.out.println(firstUser);
		System.out.println(secondUser);
		return connector.executeUpdate(query, firstUser, secondUser);
	}
}
