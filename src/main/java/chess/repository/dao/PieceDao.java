package chess.repository.dao;

import java.util.Map;

public class PieceDao {

	private static final String PIECE_NAME = "name";
	private static final String POSITION = "position";

	public void insertAll(Map<String, String> tiles, int foreignKey) {
		for (String position : tiles.keySet()) {
			insert(tiles.get(position), position, foreignKey);
		}
	}

	private void insert(String name, String position, int foreignKey) {
		ConnectionManager.createQuery("insert into piece (name, position, game_id) values (? ,?, ?)")
			.setParameter(1, name)
			.setParameter(2, position)
			.setParameter(3, foreignKey)
			.executeUpdate();
	}

	public Map<String, String> selectByGameId(int foreignKey) {
		return ConnectionManager
			.createQuery("select position, name from piece where game_id = ?")
			.setParameter(1, foreignKey)
			.executeQuery()
			.getResultMap(POSITION, PIECE_NAME);
	}

	public Map<String, String> selectByGameName(String gameName) {
		return ConnectionManager.createQuery(
				"select p.position, p.name "
					+ "from piece p join game g "
					+ "on p.game_id = g.game_id "
					+ "where g.name = ?")
			.setParameter(1, gameName)
			.executeQuery()
			.getResultMap(POSITION, PIECE_NAME);
	}

	public void deleteByPosition(String position, String gameName) {
		ConnectionManager.createQuery(
				"delete p from piece p "
					+ "join game g on p.game_id = g.game_id "
					+ "where p.position = ? and g.name = ?")
			.setParameter(1, position)
			.setParameter(2, gameName)
			.executeUpdate();
	}

	public void updatePositionOfPiece(String pieceName, String from, String to, String gameName) {
		ConnectionManager.createQuery(
				"update piece p join game g on p.game_id = g.game_id "
					+ "set p.position = ? "
					+ "where p.name = ? and p.position = ? and g.name = ?")
			.setParameter(1, to)
			.setParameter(2, pieceName)
			.setParameter(3, from)
			.setParameter(4, gameName)
			.executeUpdate();
	}
}

