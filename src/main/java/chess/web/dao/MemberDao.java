package chess.web.dao;

import chess.web.Member;
import java.util.List;

public interface MemberDao {
    void removeById(String id);

    void save(Member member);

    Member findById(String id);

    List<Member> findAll();
}
