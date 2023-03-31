package chess.repository.user;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int saveIfNotExist(String userName) {
        Optional<UserDto> user = userDao.findUserIdIfExist(userName);
        return user.map(UserDto::getUserId)
                .orElseGet(() -> userDao.save(userName));
    }
}
