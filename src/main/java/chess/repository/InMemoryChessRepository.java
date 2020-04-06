package chess.repository;

import chess.repository.entity.ChessEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryChessRepository implements ChessRepository {
    private final Map<Long, ChessEntity> memory = new Hashtable<>();
    private Long autoIncrement = 0L;

    @Override
    public ChessEntity save(ChessEntity entity) {
        autoIncrement++;
        entity = new ChessEntity(autoIncrement, LocalDateTime.now(ZoneId.of("Asia/Seoul")), entity);
        memory.put(autoIncrement, entity);
        return entity;
    }

    @Override
    public Optional<ChessEntity> findById(Long id) {
        return Optional.ofNullable(memory.get(id));
    }

    @Override
    public Long update(ChessEntity entity) {
        try {
            memory.replace(entity.getId(), entity);
            return entity.getId();
        } catch (ConcurrentModificationException e) {
            return 0L;
        }
    }

    @Override
    public List<ChessEntity> findAll() {
        return new ArrayList<>(memory.values());
    }
}
