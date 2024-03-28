package chess.repository;

import static chess.fixture.UserFixture.createUserChoco;
import static chess.fixture.UserFixture.createUserKhaki;

import chess.domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeUserDao implements UserRepository {

    private final Map<Integer, User> users = new HashMap<>();

    public FakeUserDao() {
        initialize();
    }

    @Override
    public long save(final User user) {
        if (user.getId() == null) {
            users.put(users.size() + 1, User.of((long) users.size() + 1, user.getName()));
            return users.size();
        }
        users.put(users.size() + 1, user);
        return users.size();
    }

    @Override
    public Optional<User> findByName(final String name) {
        return users.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return users.values().stream()
                .toList();
    }

    private void initialize() {
        users.put(1, createUserChoco());
        users.put(2, createUserKhaki());
    }
}
