package chess.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.entity.ChessGameEntity;

public class InMemoryChessGameDao implements ChessGameDao {

	private final Map<Long, ChessGameEntity> chessGameRepository = new HashMap<>();
	private long autoIncrement;

	public InMemoryChessGameDao() {
		this.autoIncrement = 0L;
	}

	@Override
	public long add(final ChessGameEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		autoIncrement++;

		final ChessGameEntity chessGameEntity = ChessGameEntity.of(autoIncrement, entity);

		chessGameRepository.put(autoIncrement, chessGameEntity);
		return autoIncrement;
	}

	@Override
	public long findMaxGameId() {
		return autoIncrement;
	}

	@Override
	public boolean isEmpty() {
		return chessGameRepository.isEmpty();
	}

	@Override
	public void deleteAll() {
		autoIncrement = 0L;
		chessGameRepository.clear();
	}

}
