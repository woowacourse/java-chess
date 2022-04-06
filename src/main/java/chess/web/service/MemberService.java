package chess.web.service;

import chess.web.Member;
import chess.web.dao.MemberDao;

public class MemberService {
    private MemberDao memberDao;

    public MemberService(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void save(final String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException();
        }
        if (memberDao.findById(name) == null) {
            memberDao.save(new Member("서비스테스트", "ㅋㅋㅋ", 0.0));
        }
    }
}

