package chess.dao;

import java.util.List;

import chess.domain.game.ChessGame;

public interface ChessGameDao {
	int create() throws Exception;

	List<Integer> findAll() throws Exception;

	ChessGame findById(int id) throws Exception;

	void update(int id, ChessGame chessGame) throws Exception;
}
