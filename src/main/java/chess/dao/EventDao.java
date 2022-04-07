package chess.dao;

import chess.domain.event.Event;
import chess.domain.event.EventType;
import chess.domain.event.MoveCommand;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    private static final String PROD_TABLE_NAME = "event";

    private final String table;

    EventDao(String table) {
        this.table = table;
    }

    public static EventDao ofProd() {
        return new EventDao(PROD_TABLE_NAME);
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
        while (reader.hasNextRow()) {
            String eventType = reader.readStringAt("type");
            String description = reader.readStringAt("description");
            Event event = new Event(EventType.valueOf(eventType), description);
            pieces.add(event);
        }
        return pieces;
    }

    public void saveMove(int gameId, MoveCommand moveCommand) {
        final String sql = addTable("INSERT INTO %s (game_id, type, description) VALUES (?, ?, ?)");

        new CommandBuilder(sql).setInt(gameId)
                .setString(EventType.MOVE)
                .setString(moveCommand.toDescription())
                .executeAndClose();
    }

    private String addTable(String sql) {
        return String.format(sql, table);
    }
}
