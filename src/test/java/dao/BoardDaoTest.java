package dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {
    @DisplayName("db connection확인")
    @Test
    void connectionTest() {
        Assertions.assertThat(new BoardDao().getConnection()).isNotNull();
    }
}