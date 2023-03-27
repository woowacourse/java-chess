package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DataBaseConnectionTest {

    @Test
    void 데이터베이스_커넥션이_정상_생성되면_익셉션이_발생하지_않는다() {
        assertDoesNotThrow(DataBaseConnection::getConnection);
    }
}
