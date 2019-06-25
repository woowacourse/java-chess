package chess.persistence.dao.connector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceFactoryTest {

    @Test
    void 데이터소스가_잘되는지_테스트() {
        assertNotNull(DataSourceFactory.getInstance().create());
    }

    @Test
    void DB연결이_잘되는지_테스트() {
        assertNotNull(DataSourceFactory.getInstance().getConnection());
    }

}
