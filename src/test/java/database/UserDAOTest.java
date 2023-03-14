package database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class UserDAOTest {
    
    private final UserDAO userDAO = new UserDAO();
    
    @Test
    public void connection() {
        try (final var connection = this.userDAO.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void addUser() {
        final var user = new User("testUserId", "testUser");
        this.userDAO.addUser(user);
    }
    
    @Test
    public void findByUserId() {
        final var user = this.userDAO.findByUserId("testUserId");
        
        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }
}