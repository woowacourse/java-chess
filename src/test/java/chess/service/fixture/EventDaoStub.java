package chess.service.fixture;

import chess.dao.EventDao;
import chess.domain.event.Event;
import chess.domain.event.InitEvent;
import chess.domain.event.MoveEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDaoStub extends EventDao {

    private static final String DATABASE_ACCESS_ATTEMPT_EXCEPTION_MESSAGE = "테스트 더블에서 event 테이블에 접근시도하였습니다.";

    private final Map<Integer, List<Event>> repository = new HashMap<>() {{
        put(1, new ArrayList<>(List.of(new InitEvent(), new MoveEvent("e2 e4"),
                new MoveEvent("d7 d5"), new MoveEvent("f1 b5"))));
        put(2, new ArrayList<>(List.of(new InitEvent(),
                new MoveEvent("e2 e4"), new MoveEvent("d7 d5"),
                new MoveEvent("f1 b5"), new MoveEvent("a7 a5"))));
        put(3, new ArrayList<>(List.of(new InitEvent(), new MoveEvent("e2 e4"),
                new MoveEvent("d7 d5"), new MoveEvent("f1 b5"),
                new MoveEvent("a7 a5"), new MoveEvent("b5 e8"))));
    }};

    @Override
    public List<Event> findAllByGameId(int gameId) {
        if (repository.containsKey(gameId)) {
            return repository.get(gameId);
        }
        return List.of();
    }

    @Override
    public void save(int gameId, Event event) {
        if (repository.containsKey(gameId)) {
            repository.get(gameId).add(event);
            return;
        }
        repository.put(gameId, new ArrayList<>(List.of(event)));
    }

    @Override
    protected String addTable(String sql) {
        throw new UnsupportedOperationException(DATABASE_ACCESS_ATTEMPT_EXCEPTION_MESSAGE);
    }
}
