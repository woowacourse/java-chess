package service;

import db.Movement;
import db.MovementDao;
import domain.board.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {
    final MovementDao movementDao = new MovementDao();

    public void save(final String source, final String target, final String type, final String color) {
        movementDao.createMovement(new Movement(Position.from(source), Position.from(target), type, color));
    }

    public List<Entry<Position, Position>> findPositions() {
        return movementDao.findAll().stream().map(movement -> Map.entry(movement.source(), movement.target())).toList();
    }

    public void removeAll() {
        movementDao.deleteAll();
    }

}
