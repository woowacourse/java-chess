package chess.helper;

import chess.dao.ChessMovementDao;
import chess.dao.Movement;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.List;

public class FakeChessMovementDao implements ChessMovementDao {

    private final List<Movement> movements = new ArrayList<>();

    @Override
    public List<Movement> findAll() {
        return movements;
    }

    @Override
    public void save(final Position source, final Position target) {
        final Movement movement = new Movement(source.getPosition(), target.getPosition());

        movements.add(movement);
    }

    @Override
    public void delete() {
        movements.clear();
    }
}
