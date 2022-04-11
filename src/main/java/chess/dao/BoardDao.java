package chess.dao;

import java.util.Map;

public interface BoardDao {
    void save(int gameId, Map<String, String> piecesByPositions);

    Map<String, String> findById(int gameId);

    void deleteById(int gameId);
}
