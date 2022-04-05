package chess.dao;

import chess.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberDao {
    Long save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();
}
