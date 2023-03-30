package chess.dao;

import java.util.Set;

public interface GameRoomDao {
    Set<Integer> findExistingRoomNumbers();
}
