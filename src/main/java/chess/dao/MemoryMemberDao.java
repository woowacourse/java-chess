package chess.dao;

import chess.domain.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberDao implements MemberDao {
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static int nextId = 1;

    @Override
    public Long save(Member member) {
        member = new Member((long) nextId++, member.getName());
        store.put(member.getId(), member);
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    void deleteAll() {
        store.clear();
    }
}
