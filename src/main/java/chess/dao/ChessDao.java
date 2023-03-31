package chess.dao;

import chess.domain.Board;

public interface ChessDao {
	void save(Board board);

	Board find();

	void update(Board board);

	void init();
}
