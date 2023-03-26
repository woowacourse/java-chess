package chess.dao.chess;

import chess.entity.ChessGameEntity;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MockChessGameDao implements ChessGameDao {
    private final Map<Long, ChessGameEntity> STORAGE = new ConcurrentHashMap<>();
    private final AtomicLong pk = new AtomicLong(0L);

    @Override
    public Optional<ChessGameEntity> findByUserId(final Long userId) {
        return STORAGE.entrySet().stream()
                .filter(entry -> entry.getValue().getUserId().equals(userId))
                .map(entry -> new ChessGameEntity(entry.getKey(),
                        entry.getValue().getCurrentCamp(), entry.getValue().getUserId()))
                .findFirst();
    }

    @Override
    public Long save(final ChessGameEntity chessGameEntity) {
        STORAGE.put(pk.addAndGet(1L), chessGameEntity);
        return pk.longValue();
    }
}
