package chess.db;

import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public class FakeStatusDAO {

    private final Map<String, String> db;

    public FakeStatusDAO() {
        this.db = new HashMap<>();
    }

    public void insertId(String roomId) {
        db.put(roomId, Color.WHITE.name());
    }

    public boolean isSaved(String roomId) {
        return db.containsKey(roomId);
    }

    public void convertColor(String roomId) {
        Color color = Color.valueOf(db.get(roomId));
        if (color == Color.WHITE) {
            db.put(roomId, Color.BLACK.name());
            return;
        }
        db.put(roomId, Color.WHITE.name());
    }

    public Color getColor(String roomId) {
        return Color.valueOf(db.get(roomId));
    }

    public void terminate() {
        for (String key : db.keySet()) {
            db.remove(key);
        }
    }

    public int getSize() {
        return db.size();
    }
}
