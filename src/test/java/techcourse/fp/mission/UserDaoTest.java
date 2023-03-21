package techcourse.fp.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

class UserDaoTest {
    final UserDao userDao = new UserDao();

    @Test
    public void connection() {
       final Connection connection = userDao.getConnection();
       assertThat(connection).isNotNull();
    }

    @Test
    void save(){
//        final User user = new User("neo2", "neo2");
//        userDao.insert(user);
    }

    @Test
    void findById(){
        final User neo = userDao.findByUserId("neo");
        assertThat(neo).isEqualTo(new User("neo", "neo"));
    }

    @Test
    void notFound(){
        final User user = userDao.findByUserId("hi");
        assertThat(user).isNull();
    }

    @Test
    void updateTest(){
        final User changedNeo = userDao.findByUserId("neo");
        userDao.update(changedNeo);
        assertThat(changedNeo.name()).isEqualTo("changedName");
    }

    @Test
    void deleteTest(){
        final User deleteNeo = userDao.findByUserId("neo");
        userDao.delete(deleteNeo);
        assertThat(deleteNeo).isNotNull();
    }
}
