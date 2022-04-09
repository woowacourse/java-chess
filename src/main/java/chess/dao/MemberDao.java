package chess.dao;

import java.util.List;

public interface MemberDao<T> extends Dao<T> {

    void saveAll(List<T> targets, int boardId);

    List<T> getAllByBoardId(int boardId);

    T save(String name, int boardId);
}
