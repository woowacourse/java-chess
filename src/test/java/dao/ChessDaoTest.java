package dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessDaoTest {
    @DisplayName("db connection확인")
    @Test
    void connectionTest() {
        Assertions.assertThat(new ChessGameDao().getConnection()).isNotNull();
    }
}