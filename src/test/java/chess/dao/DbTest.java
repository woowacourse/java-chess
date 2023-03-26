package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DbTest {

    @Test
    @DisplayName("데이터베이스 커넥션 객체를 생성한다.")
    void getConnection() {
        // given
        Db db = new Db();

        // when, then
        assertThat(db.getConnection()).isInstanceOf(Connection.class);
    }
}
