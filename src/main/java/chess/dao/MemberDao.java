package chess.dao;

import chess.Member;
import java.util.List;

public interface MemberDao<T> {

    List<T> getAllByBoardId(int boardId);

    Member save(String name, int boardId);

    void saveAll(List<T> members, int boardId);
}
