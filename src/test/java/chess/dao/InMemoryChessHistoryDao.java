package chess.dao;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.entity.ChessHistoryEntity;

public class InMemoryChessHistoryDao implements ChessHistoryDao {

	private final Map<Long, ChessHistoryEntity> chessHistoryRepository = new HashMap<>();
	private long autoIncrement;

	public InMemoryChessHistoryDao() {
		this.autoIncrement = 0L;
	}

	@Override
	public List<ChessHistoryEntity> findAllByGameId(final long gameId) {
		return chessHistoryRepository.values().stream()
			.filter(entity -> entity.getGameId() == gameId)
			.collect(toList());
	}

	@Override
	public void add(final ChessHistoryEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		autoIncrement++;

		final ChessHistoryEntity chessHistoryEntity = ChessHistoryEntity.of(autoIncrement, entity);

		chessHistoryRepository.put(autoIncrement, chessHistoryEntity);
	}

	@Override
	public void deleteAll() {
		chessHistoryRepository.clear();
	}

}
