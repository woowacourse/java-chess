package chess.service;

import chess.dao.MemberDaoImpl;
import chess.domain.Member;

public class MemberService {
    private MemberDaoImpl memberDaoImpl = new MemberDaoImpl();

    public MemberService() {
    }

    public MemberService(final MemberDaoImpl memberDaoImpl) {
        this.memberDaoImpl = memberDaoImpl;
    }

    public void save(final String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException();
        }
        //dao메서드 findById() 추가
        if (memberDaoImpl.findById(name) == null) {
            memberDaoImpl.save(new Member("서비스테스트", "ㅋㅋㅋ", 0.0));
        }
    }
}

