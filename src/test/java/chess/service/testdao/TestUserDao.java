package chess.service.testdao;

import chess.dao.UserDaoInterface;
import chess.domain.user.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TestUserDao implements UserDaoInterface {

    private final List<User> users;
    private long generatedKey;

    public TestUserDao() {
        this.generatedKey = 1;
        this.users = new ArrayList<>();
    }

    @Override
    public void insert(final String name) {
        users.add(
            new User(generatedKey++, name, 0, 0, LocalDateTime.now())
        );
    }

    @Override
    public Optional<User> selectById(final long id) {
        return users.stream()
            .filter(user -> Objects.equals(user.getId(), id))
            .findAny();
    }

    @Override
    public Optional<User> selectByName(final String name) {
        return users.stream()
            .filter(user -> Objects.equals(user.getName(), name))
            .findAny();
    }

    @Override
    public void updateResult(final long winnerId, final long loserId) {

    }

    @Override
    public void deleteByName(final String name) {

    }
}

