package chess.repository.dao;

import java.util.List;

import chess.domain.ChessGame;

public class ChessGameDao {

	private static final String STATE = "state";
	private static final String NAME = "name";

	public int insert(ChessGame game) {
		return ConnectionManager.createQuery("insert into chessGame (name, state) values (? ,?)")
			.setParameter(1, game.getName())
			.setParameter(2, game.getState().toString())
			.executeUpdate()
			.getGeneratedKey();
	}

	public String selectStateByName(String name) {
		return ConnectionManager.createQuery("select state from chessGame where name = ?")
			.setParameter(1, name)
			.executeQuery()
			.getResult(STATE);
	}

	public void delete(String name) {
		ConnectionManager.createQuery("delete from chessGame where name = ?")
			.setParameter(1, name)
			.executeUpdate();
	}

	public List<String> selectAllNames() {
		return ConnectionManager.createQuery("select name from chessGame")
			.executeQuery()
			.getResultList(NAME);
	}

	public void updateState(ChessGame game) {
		ConnectionManager.createQuery("update chessgame set state = ? where name = ?")
			.setParameter(1, game.getState().toString())
			.setParameter(2, game.getName())
			.executeUpdate();
	}
}
