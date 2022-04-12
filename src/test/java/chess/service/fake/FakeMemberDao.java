package chess.service.fake;

import chess.Member;
import chess.dao.MemberDao;
import java.util.List;

public class FakeMemberDao implements MemberDao<Member> {

    private int fakeAutoIncrementId;
    private String fakeName;

    public FakeMemberDao(int fakeAutoIncrementId, String fakeName) {
        this.fakeAutoIncrementId = fakeAutoIncrementId;
        this.fakeName = fakeName;
    }

    @Override
    public List<Member> getAllByRoomId(int roomId) {
        return List.of(
                new Member(fakeAutoIncrementId, fakeName, roomId),
                new Member(fakeAutoIncrementId + 1, fakeName + 1, roomId));
    }

    @Override
    public Member save(String name, int roomId) {
        return null;
    }

    @Override
    public void saveAll(List<Member> members, int roomId) {

    }
}
