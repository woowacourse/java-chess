package chess.dao;

import java.util.List;

public interface MoveDao {

    void saveAll(final QueryStrategy saveStrategy);

    List<Move> findAll(final MoveFindAllStrategy moveFindAllStrategy);
}
