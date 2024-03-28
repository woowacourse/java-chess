package user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("localhost 환경에 의존적이므로 비활성화")
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @BeforeEach
    void setUp() {
        userDao.addUser(new User("pobiconan", "pobi"));
        userDao.addUser(new User("sugarbrown", "brown"));
    }

    @AfterEach
    void tearDown() {
        userDao.deleteAll();
    }

    @Test
    void connection() {
        UserDao userDao = new UserDao();
        var connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void addUser() {
        UserDao userDao = new UserDao();
        userDao.addUser(new User("black", "prin"));
        assertThat(userDao.findById("black")).isEqualTo(new User("black", "prin"));
    }

    @Test
    void findAll() {
        UserDao userDao = new UserDao();
        var users = userDao.findAll();
        assertThat(users).containsExactly(
                new User("pobiconan", "pobi"),
                new User("sugarbrown", "brown")
        );
    }

    @Test
    void findById() {
        UserDao userDao = new UserDao();
        var user = userDao.findById("pobiconan");
        assertThat(user).isEqualTo(new User("pobiconan", "pobi"));
    }

    @Test
    void updateUser() {
        UserDao userDao = new UserDao();
        userDao.updateUser(new User("pobiconan", "pobi2"));
        assertThat(userDao.findById("pobiconan")).isEqualTo(new User("pobiconan", "pobi2"));
    }

    @Test
    void deleteUser() {
        UserDao userDao = new UserDao();
        userDao.deleteById("pobiconan");
        assertThat(userDao.findById("pobiconan")).isNull();
    }
}
