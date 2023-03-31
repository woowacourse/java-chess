package chess.dao;

import chess.model.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMoveDao implements MoveDao {

    private final Map<Long, Move> moves;
    private final AtomicLong atomicLong;

    public FakeMoveDao() {
        this.moves = new HashMap<>();
        this.atomicLong = new AtomicLong();
    }

    @Override
    public void save(final Position source, final Position target) {
        moves.put(atomicLong.incrementAndGet(), new Move(source.getPosition(), target.getPosition()));
    }

    @Override
    public List<Move> findAll() {
        return new ArrayList<>(moves.values());
    }

    @Override
    public void truncateMove() {
        moves.clear();
    }

    @Override
    public boolean hasGame() {
        return !moves.isEmpty();
    }
}
