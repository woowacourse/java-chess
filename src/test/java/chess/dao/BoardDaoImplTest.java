package chess.dao;

import java.sql.Connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoImplTest {

    @Test
    @DisplayName("Connection 동작을 검증한다.")
    void connection() {
        //given
        BoardDao boardDaoImpl = new BoardDaoImpl();

        //when
        Connection connection = boardDaoImpl.getConnection();

        //then
        Assertions.assertThat(connection).isNotNull();
    }
}
