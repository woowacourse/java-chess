package chess.util;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    @Test
    void DB연결() {
        DBConnection.getConnection();
    }

}
