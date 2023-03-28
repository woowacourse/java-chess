package chess.dao;

import java.util.List;

public interface ChessStatusDao {
    List<String> readGameIds();

    String readTurn(final String gameId);

    String create(final String turn);

    void update(final String turn, final String gameId);
}
