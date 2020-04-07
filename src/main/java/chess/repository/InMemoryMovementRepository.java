package chess.repository;

import chess.entity.Movement;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryMovementRepository implements MovementRepository {

    private final Map<Long, Movement> memory = new Hashtable<>();
    private Long autoIncrement = 0L;

    @Override
    public Movement save(Movement entity) {
        autoIncrement++;
        entity = new Movement(autoIncrement, entity, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        memory.put(autoIncrement, entity);
        return entity;
    }

    @Override
    public List<Movement> findAllByChessId(Long chessId) {
        return memory.values()
                .stream()
                .filter(entity -> entity.equalGameId(chessId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        memory.clear();
    }
}
