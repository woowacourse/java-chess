package dao;

import chessgame.dao.ConnectionGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConnectionGeneratorTest {
    @Test
    void connection() {
        assertThat(ConnectionGenerator.getConnection()).isNotNull();
    }
}
