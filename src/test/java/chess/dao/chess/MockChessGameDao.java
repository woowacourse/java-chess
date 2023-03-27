package chess.dao.chess;

import chess.domain.chess.CampType;
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
        return STORAGE.values().stream()
                .filter(chessGameEntity -> chessGameEntity.getUserId().equals(userId))
                .map(chessGameEntity -> new ChessGameEntity(chessGameEntity.getId(), chessGameEntity.getCurrentCamp(),
                        chessGameEntity.getUserId()))
                .findFirst();
    }

    @Override
    public Long save(final ChessGameEntity chessGameEntity) {
        STORAGE.put(pk.addAndGet(1L), chessGameEntity);
        return pk.longValue();
    }

    @Override
    public void updateCurrentCampById(final Long id, final CampType currentCamp) {
        final ChessGameEntity chessGameEntity = STORAGE.get(id);
        STORAGE.put(id, new ChessGameEntity(currentCamp.name(), chessGameEntity.getUserId()));
    }
}
