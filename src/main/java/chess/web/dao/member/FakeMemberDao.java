package chess.web.dao.member;

import chess.web.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeMemberDao implements MemberDao {

    private final HashMap<String, Member> members = new HashMap<>();


    @Override
    public void removeById(final String id) {
        members.remove(id);
    }

    @Override
    public void save(final Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(final String id) {
        return members.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }
}
