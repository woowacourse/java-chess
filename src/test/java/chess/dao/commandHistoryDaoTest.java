package chess.dao;

import chess.domains.CommandHistory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class commandHistoryDaoTest {
    private static CommandHistoryDao commandHistoryDao;

    @BeforeAll
    public static void setup() {
        commandHistoryDao = new CommandHistoryDao();
    }

    @DisplayName("연결 가능 여부 테스트")
    @Test
    public void connection() {
        Connection con = commandHistoryDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("레코드 갯수 카운트 테스트")
    @Test
    public void countRecords() {
        assertThat(commandHistoryDao.countRecords()).isEqualTo(1);
    }

    @DisplayName("레코드 추가 테스트")
    @Test
    public void addRecord() {
        CommandHistory commandHistory = new CommandHistory("start", "", "", "");
        commandHistoryDao.addRecord(commandHistory);
    }
}