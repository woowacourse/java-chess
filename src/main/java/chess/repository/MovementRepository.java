package chess.repository;

import chess.domain.square.Movement;
import java.util.List;

public interface MovementRepository {

    void save(long roomId, Movement movement);

    List<Movement> findAllByRoomId(long roomId);
}
