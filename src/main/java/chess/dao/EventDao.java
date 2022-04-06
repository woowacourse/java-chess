package chess.dao;

import chess.domain.event.MoveCommand;
import chess.domain.event.Event;
import chess.domain.event.EventType;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    private final String table;

    public EventDao(String table) {
        this.table = table;
    }

    public void saveMove(int gameId, MoveCommand moveCommand) {
        final String sql = addTable("INSERT INTO %s (game_id, type, description) VALUES (?, ?, ?)");

        new CommandBuilder(sql).setInt(gameId)
                .setString(EventType.MOVE)
                .setString(moveCommand.toDescription())
                .executeAndClose();
    }

    public List<Event> findAllByGameId(int gameId) {
        final String sql = addTable("SELECT type, description FROM %s WHERE game_id = ?");
        final ResultReader reader = new QueryBuilder(sql).setInt(gameId)
                .execute();

        try (reader) {
            return readAllEvents(reader);
        }
    }

    private List<Event> readAllEvents(ResultReader reader) {
        List<Event> pieces = new ArrayList<>();
        while (reader.nextRow()) {
            String eventType = reader.readStringAt("type");
            String description = reader.readStringAt("description");
            Event event = new Event(EventType.valueOf(eventType), description);
            pieces.add(event);
        }
        return pieces;
    }

    private String addTable(String sql) {
        return String.format(sql, table);
    }
}
