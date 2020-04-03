package chess.repository;

import chess.repository.entity.ChessEntity;

import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class InMemoryChessRepository implements ChessRepository {
    private final Map<Long, ChessEntity> memory = new Hashtable<>();
    private Long autoIncrement = 0L;

    @Override
    public ChessEntity save(ChessEntity entity) {
        autoIncrement++;
        entity = new ChessEntity(autoIncrement, entity);
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
}
