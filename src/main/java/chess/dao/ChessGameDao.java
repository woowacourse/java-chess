package chess.dao;

import chess.domain.piece.Color;

public interface ChessGameDao {

    Long save(final Color turn);

    Color findTurnById(final Long id);

    void deleteById(final Long id);

    void updateTurnById(final Long id, final String turn);
}
