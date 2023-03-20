package techcourse.fp.database;

class UserDaoTest {

    /*UserDao userDao;

    @BeforeEach
    void init() {
        userDao = new UserDao();
    }

    @Test
    void connection() {
        final Connection connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void insertTest() {
        final UserDao userDao = new UserDao();
        final User user = new User("id", "name");
        userDao.insert(user);
    }

    @Test
    void findByUserId() {
        final User user = userDao.findByUser("id");
        assertThat(user).isNotNull();
    }

    @Test
    void notFount() {
        final User user = userDao.findByUser("some");
        assertThat(user).isNull();
    }

    @Test
    void updateTest() {
        userDao.update("id", "hubcreator");
        final User user = userDao.findByUser("id");
        assertThat(user.userName()).isEqualTo("hubcreator");
    }

    @Test
    void deleteTest() {
        userDao.delete("id");
        assertThat(userDao.findByUser("id")).isNull();
    }*/
}
