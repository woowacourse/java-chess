package chess.dao;

import java.util.List;

public interface MoveDao {

    void saveAll(final MoveQueryStrategy saveStrategy);

    List<Move> findAll(final MoveFindAllStrategy moveFindAllStrategy);
}
