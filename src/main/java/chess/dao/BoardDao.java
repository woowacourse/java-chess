package chess.dao;

import java.util.Map;

public interface BoardDao {
    void save(int gameId, Map<String, String> StringPieceMapByPiecesByPositions);
}
