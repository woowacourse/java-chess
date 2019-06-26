package chess.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataSourceFactoryTest {

    @Test
    void create() {
        assertDoesNotThrow(() -> DataSourceFactory.getInstance().createDataSource());
    }
}
