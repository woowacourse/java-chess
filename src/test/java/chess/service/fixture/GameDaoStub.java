package chess.service.fixture;

import chess.dao.GameDao;
import chess.dao.GameState;
import java.util.HashMap;
import java.util.Map;

public class GameDaoStub extends GameDao {

    private static final String DATABASE_ACCESS_ATTEMPT_EXCEPTION_MESSAGE = "테스트 더블에서 game 테이블에 접근시도하였습니다.";

    private int autoIncrementId = 3;
    private final Map<Integer, GameState> repository = new HashMap<>() {{
        put(1, GameState.RUNNING);
        put(2, GameState.RUNNING);
        put(3, GameState.OVER);
    }};

    @Override
    public int saveAndGetGeneratedId() {
        autoIncrementId++;
        repository.put(autoIncrementId, GameState.RUNNING);
        return autoIncrementId;
    }

    @Override
    public void finishGame(int gameId) {
        repository.put(gameId, GameState.OVER);
    }

    @Override
    public boolean checkById(int gameId) {
        return repository.containsKey(gameId);
    }

    @Override
    public int countAll() {
        return repository.values().size();
    }

    @Override
    public int countByState(GameState state) {
        return (int) repository.values()
                .stream()
                .filter(value -> value == state)
                .count();
    }

    @Override
    protected String addTable(String sql) {
        throw new UnsupportedOperationException(DATABASE_ACCESS_ATTEMPT_EXCEPTION_MESSAGE);
    }
}
