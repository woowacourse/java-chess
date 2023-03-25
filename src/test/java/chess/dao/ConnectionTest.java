package chess.dao;

import chess.dao.ConnectionGenerator;
import java.sql.Connection;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ConnectionTest {

    @Test
    void DB_연결을_확인한다() {
        // given
        Connection connection = ConnectionGenerator.getConnection();

        // expect
        assertThat(connection).isNotNull();
    }
}
