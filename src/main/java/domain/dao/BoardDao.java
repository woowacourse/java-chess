package domain.dao;

import domain.type.Color;

public interface BoardDao {

    Color findLastColor(final String id);

    Integer count(final String id);

    Void insert(final String boardId, final Color color);

    Integer update(final String boardId, final Color color);
}
