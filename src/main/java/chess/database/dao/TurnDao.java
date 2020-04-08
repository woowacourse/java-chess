package chess.database.dao;

import chess.domain.piece.Color;

public interface TurnDao {
	void insert(String turn);

	void update(String turn);

	Color getTurn();

	void deleteTurn();
}
