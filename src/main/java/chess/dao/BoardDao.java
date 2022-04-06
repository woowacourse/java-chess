package chess.dao;

import chess.piece.detail.Color;

public interface BoardDao {

    void save(final Color color);

    Color findById(final int id);
}
