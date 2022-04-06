package chess.service;

import java.util.Map;

public interface ChessDao {
    Map<String, String> getBoardByGameId(String gameId);

    void move(String gameId, String from, String to, String piece);

    int getTurnByGameId(String gameId);
}
