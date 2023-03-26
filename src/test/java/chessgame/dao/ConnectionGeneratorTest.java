package chessgame.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConnectionGeneratorTest {
    @Test
    @DisplayName("DataBase Connection이 잘 수행되는지 확인 테스트한다.")
    void connection() {
        assertThat(ConnectionGenerator.getConnection()).isNotNull();
    }
}
