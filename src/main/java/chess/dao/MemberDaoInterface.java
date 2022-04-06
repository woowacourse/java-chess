package chess.dao;

import chess.domain.Member;
import java.util.List;

public interface MemberDaoInterface {
    void removeById(String id);

    void save(Member member);

    Member findById(String id);

    List<Member> findAll();
}
