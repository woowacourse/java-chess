package chess.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;

public class InMemoryChessGameDao implements ChessGameDao {
	private Map<Integer, ChessGame> memory;

	public InMemoryChessGameDao() {
		this.memory = new HashMap<>();
	}

	@Override
	public int create() throws Exception {
		int id = memory.size() + 1;
		memory.put(id, new ChessGame(new Ready()));
		return id;
	}

	@Override
	public List<Integer> findAll() throws Exception {
		return new ArrayList<>(memory.keySet());
	}

	@Override
	public ChessGame findById(int id) throws Exception {
		return memory.get(id);
	}

	@Override
	public void update(int id, ChessGame chessGame) throws Exception {
		memory.replace(id, chessGame);
	}
}
