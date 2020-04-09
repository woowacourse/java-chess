package chess.dao;

import chess.domains.Record;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RecordDaoTest {
    private static RecordDao recordDao;

    @BeforeAll
    public static void setup() {
        recordDao = new RecordDao();
    }

    @DisplayName("연결 가능 여부 테스트")
    @Test
    public void connection() throws SQLException, ClassNotFoundException {
        Connection con = recordDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("레코드 갯수 카운트 테스트")
    @Test
    public void countRecords() throws Exception {
        assertThat(recordDao.countRecords()).isEqualTo(1);
    }

    @DisplayName("레코드 추가 테스트")
    @Test
    public void addRecord() throws Exception {
        Record record = new Record("start", "");
        recordDao.addRecord(record);
    }
}