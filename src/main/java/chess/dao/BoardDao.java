package chess.dao;

import chess.domain.Board;

public interface BoardDao {

    void saveAll(final Long gameId, final Board board);

    void save(final String name, final String position, final Long gameId);

    Board findById(final Long gameId);

    void deleteByGameId(final Long gameId);

    void updateNameByGameIdAndPosition(final Long gameId, final String position, final String name);
}
