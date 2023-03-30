package chess.dao;

import java.util.List;

public interface GameRoomDao {
    List<Integer> findExistingRoomNumbers();
}
