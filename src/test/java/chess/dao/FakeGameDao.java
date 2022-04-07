package chess.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.game.GameState;

public class FakeGameDao implements GameDao {

    private static final int STATE_INDEX = 0;
    private static final int COLOR_INDEX = 1;

    private final Map<String, List<String>> memoryDatabase;

    public FakeGameDao() {
        this.memoryDatabase = new HashMap<>();
    }

    @Override
    public void saveGame(String state, Color color, String roomName) {
        memoryDatabase.put(roomName, Arrays.asList(state, color.name()));
    }

    @Override
    public List<String> readStateAndColor(String roomName) {
        return memoryDatabase.get(roomName);
    }

    @Override
    public void updateState(GameState state, String roomName) {
        List<String> target = memoryDatabase.get(roomName);
        target.set(STATE_INDEX, state.getState());
        target.set(COLOR_INDEX, state.getColor().name());
    }
}
