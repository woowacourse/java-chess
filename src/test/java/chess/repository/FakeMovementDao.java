package chess.repository;

import static chess.fixture.MovementFixture.createMovements;

import chess.domain.square.Movement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeMovementDao implements MovementRepository {

    private final Map<Long, List<Movement>> movements = new HashMap<>();

    public FakeMovementDao() {
        initialize();
    }

    @Override
    public void save(final long roomId, final Movement movement) {
        if (movements.get(roomId) == null) {
            movements.put(roomId, new ArrayList<>(List.of(movement)));
            return;
        }
        movements.get(roomId).add(movement);
    }

    @Override
    public List<Movement> findAllByRoomId(final long roomId) {
        return movements.get(roomId);
    }

    private void initialize() {
        movements.put(1L, createMovements());
    }
}
