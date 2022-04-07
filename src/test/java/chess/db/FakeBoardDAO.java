package chess.db;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class FakeBoardDAO {

    private Map<String, String> db;
    private String roomId;

    public FakeBoardDAO(String roomId) {
        this.roomId = roomId;
        this.db = new HashMap<>();
    }

    private String getKey(Position position) {
        return position.getPosition() + " " + roomId;
    }

    private Position convertPosition(String key) {
        return new Position(key.split(" ")[0]);
    }

    private String getValue(Piece piece) {
        return piece.getName() + " " + piece.getColor() + " " + roomId;
    }

    private Piece convertPiece(String value) {
        String name = value.split(" ")[0];
        String color = value.split(" ")[1];
        return PieceGenerator.of(name, color);
    }

    public void insert(Position position, Piece piece) {
        db.put(getKey(position), getValue(piece));
    }

    public void delete(Position position) {
        db.remove(getKey(position));
    }

    public void deleteById(String roomId) {
        for (String key : db.keySet()) {
            String id = key.split(" ")[1];
            if (id.equals(roomId)) {
                db.remove(key);
            }
        }
    }

    public Map<Position, Piece> findAllPieces() {
        Map<Position, Piece> res = new HashMap<>();
        for (String key : db.keySet()) {
            res.put(convertPosition(key), convertPiece(db.get(key)));
        }
        return res;
    }

    public boolean isInDB(String key) {
        return db.containsKey(key);
    }

    public int getSize() {
        return this.db.size();
    }
}
