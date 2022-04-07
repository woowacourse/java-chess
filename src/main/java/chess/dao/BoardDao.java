package chess.dao;

import java.util.Map;

public interface BoardDao {

    void update(String position, String piece);

    Map<String, String> getBoard();

    void reset(Map<String, String> board);
}
