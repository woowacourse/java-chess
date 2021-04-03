package chess.domain.dao;

import chess.domain.entity.Chess;

public interface ChessDao {
    void save(final Chess chess);

    Chess findByName(final String name);

    void deleteByName(final String name);
}
