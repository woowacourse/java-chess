package chess.repository.dao;

import java.util.Map;

public class TileDao {

	private static final String POSITION = "position";
	private static final String PIECE = "piece";

	public void insertAll(Map<String, String> tiles, int foreignKey) {
		for (String position : tiles.keySet()) {
			insert(position, tiles.get(position), foreignKey);
		}
	}

	private void insert(String position, String piece, int foreignKey) {
		ConnectionManager.createQuery("insert into tile (position, piece, game_id) values (? ,?, ?)")
			.setParameter(1, position)
			.setParameter(2, piece)
			.setParameter(3, foreignKey)
			.executeUpdate();
	}

	public Map<String, String> selectByGameId(int foreignKey) {
		return ConnectionManager
			.createQuery("select position, piece from tile where game_id = ?")
			.setParameter(1, foreignKey)
			.executeQuery()
			.getResultMap(POSITION, PIECE);
	}

	public Map<String, String> selectByGameName(String gameName) {
		return ConnectionManager
			.createQuery("select t.position, t.piece from tile t natural join game g where g.name = ?")
			.setParameter(1, gameName)
			.executeQuery()
			.getResultMap(POSITION, PIECE);
	}

	public void deleteByPosition(String position, String gameName) {
		ConnectionManager.createQuery(
				"delete t from tile t natural join game g "
					+ "where t.position = ? and g.name = ?")
			.setParameter(1, position)
			.setParameter(2, gameName)
			.executeUpdate();
	}

	public void updatePositionOfPiece(String pieceName, String from, String to, String gameName) {
		ConnectionManager.createQuery(
				"update tile t natural join game g set t.position = ? "
					+ "where t.piece = ? and t.position = ? and g.name = ?")
			.setParameter(1, to)
			.setParameter(2, pieceName)
			.setParameter(3, from)
			.setParameter(4, gameName)
			.executeUpdate();
	}
}

