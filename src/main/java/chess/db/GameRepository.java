package chess.db;

import chess.domain.game.Game;
import java.util.HashMap;
import java.util.Map;

public class GameRepository {

    private static final String GAME_NOT_FOUND_EXCEPTION_MESSAGE = "존재하지 않는 게임입니다.";

    private final Map<Integer, Game> database = new HashMap<>();
    private int id = 0;

    public int add(Game game) {
        database.put(++id, game);
        return id;
    }

    public Game findById(int id) {
        if (!database.containsKey(id)) {
            throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return database.get(id);
    }

    public void update(int id, Game newGame) {
        if (!database.containsKey(id)) {
            throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        database.put(id, newGame);
    }
}
