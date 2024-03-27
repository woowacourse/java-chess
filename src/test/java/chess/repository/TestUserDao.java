package chess.repository;

import static chess.fixture.UserFixture.createUserChoco;
import static chess.fixture.UserFixture.createUserKhaki;

import chess.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUserDao implements UserRepository {

    private final List<User> users = new ArrayList<>();

    public TestUserDao() {
        initialize();
    }

    @Override
    public long save(final User user) {
        if (user.getId() == null) {
            users.add(new User((long) users.size() + 1, user.getName()));
            return users.size();
        }
        users.add(new User(user.getName()));
        return users.size();
    }

    @Override
    public Optional<User> findByName(final String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findAny();
    }

    @Override
    public void deleteAll() {
        users.clear();
        initialize();
    }

    private void initialize() {
        users.add(createUserChoco());
        users.add(createUserKhaki());
    }
}
