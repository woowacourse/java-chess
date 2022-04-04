package chess.repository.dao;

import java.util.List;

public class ChessGameDao {

	private static final String STATE = "state";
	private static final String NAME = "name";

	public int insert(String name, String state) {
		return ConnectionManager.createQuery("insert into game (name, state) values (? ,?)")
			.setParameter(1, name)
			.setParameter(2, state)
			.executeUpdate()
			.getGeneratedKey();
	}

	public String selectStateByName(String name) {
		return ConnectionManager.createQuery("select state from game where name = ?")
			.setParameter(1, name)
			.executeQuery()
			.getResult(STATE);
	}

	public void delete(String name) {
		ConnectionManager.createQuery("delete from game where name = ?")
			.setParameter(1, name)
			.executeUpdate();
	}

	public List<String> selectAllNames() {
		return ConnectionManager.createQuery("select name from game")
			.executeQuery()
			.getResultList(NAME);
	}

	public void updateState(String name, String state) {
		ConnectionManager.createQuery("update game set state = ? where name = ?")
			.setParameter(1, state)
			.setParameter(2, name)
			.executeUpdate();
	}
}
