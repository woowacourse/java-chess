package chess.dao;

import chess.model.position.Position;
import java.util.List;

public interface ChessMovementDao {

    List<Movement> findAll();

    void save(final Position source, final Position target);

    void delete();
}
