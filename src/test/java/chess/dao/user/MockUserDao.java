package chess.dao.user;

import chess.entity.UserEntity;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MockUserDao implements UserDao {
    private final Map<Long, UserEntity> STORAGE = new ConcurrentHashMap<>();
    private final AtomicLong pk = new AtomicLong(0L);

    @Override
    public Optional<UserEntity> findByName(final String name) {
        return STORAGE.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(name))
                .map(entry -> new UserEntity(entry.getKey(), entry.getValue().getName()))
                .findFirst();
    }

    @Override
    public Long save(final String name) {
        STORAGE.put(pk.addAndGet(1L), new UserEntity(name));
        return pk.longValue();
    }
}
