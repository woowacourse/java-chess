package chess.service;

import chess.dao.MemberDao;
import chess.domain.Member;
import java.util.List;

public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public List<Member> findAllMembers() {
        return memberDao.findAll();
    }

    public void addMember(String memberName) {
        memberDao.save(new Member(memberName));
    }
}
