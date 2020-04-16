package chess.dao;

import chess.entity.ChessGameEntity;

public interface ChessGameDao {

	long add(final ChessGameEntity entity);

	long findMaxGameId();

	boolean isEmpty();

	void deleteAll();

}
