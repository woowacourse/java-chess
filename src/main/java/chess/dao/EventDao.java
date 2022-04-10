package chess.dao;

import chess.domain.event.Event;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    private static final String TABLE_NAME = "event";

    public List<Event> findAllByGameId(int gameId) {
        final String sql = addTable("SELECT type, description FROM %s WHERE game_id = ?");
        final ResultReader reader = new StatementExecutor(sql).setInt(gameId)
                .executeQuery();
        try (reader) {
            return readAllEvents(reader);
        }
    }

    private List<Event> readAllEvents(ResultReader reader) {
        List<Event> pieces = new ArrayList<>();
        while (reader.hasNextRow()) {
            String eventType = reader.readStringAt("type");
            String description = reader.readStringAt("description");
            Event event = Event.of(eventType, description);
            pieces.add(event);
        }
        return pieces;
    }

    public void save(int gameId, Event event) {
        final String sql = addTable("INSERT INTO %s (game_id, type, description) VALUES (?, ?, ?)");

        new StatementExecutor(sql).setInt(gameId)
                .setString(event.getType())
                .setString(event.getDescription())
                .executeCommand();
    }

    protected String addTable(String sql) {
        return String.format(sql, TABLE_NAME);
    }
}
