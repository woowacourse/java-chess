package chess.dao;

import chess.model.position.Position;
import java.util.List;

public interface MoveDao {

    void save(final Position source, final Position target);

    List<Move> findAll();

    boolean hasGame();

    void truncateMove();
}
