package chess.database.dao;

import chess.domain.piece.Color;

public interface TurnDao {
	void insert(Color turn);

	void update(Color turn);

	Color getTurn();

	void deleteTurn();
}
