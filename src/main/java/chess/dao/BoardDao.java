package chess.dao;

import chess.piece.detail.Color;

public interface BoardDao {

    void save(final Color color);

    Color findTurnById(final int id);

    Color load();

    int findLastlyUsedBoard();

    void updateById(int boardId, Color turn);

    void deleteById(int boardId);
}
