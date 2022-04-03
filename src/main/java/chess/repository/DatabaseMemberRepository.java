package chess.repository;

import chess.dao.MemberDao;
import chess.domain.Member;
import java.util.List;
import java.util.Optional;

public class DatabaseMemberRepository implements MemberRepository {
    private final MemberDao memberDao;

    public DatabaseMemberRepository(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void save(Member member) {
        memberDao.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(memberDao.findById(id));
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }
}
