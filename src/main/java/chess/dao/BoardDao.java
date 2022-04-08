package chess.dao;

import java.util.Map;

public interface BoardDao {

    Map<String, String> getBoard();

    void updatePosition(final String position, final String piece);

    void updateBatchPositions(final Map<String, String> board);
}
