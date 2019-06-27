package chess.dao;

import chess.service.dto.HistoryDto;
import org.junit.jupiter.api.Test;

import java.sql.SQLDataException;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

class HistoryDaoTest {
    @Test
    void selectLastHistory() throws SQLDataException {
        assertThat(HistoryDao.getInstance().selectLastHistory(1)).isNotNull();
    }

    @Test
    void insertHistory() throws SQLDataException {
        HistoryDto historyDTO = new HistoryDto();
        historyDTO.setRound(1);
        historyDTO.setRows(Arrays.asList(
                "RNBKQBKR",
                "PPPPPPPP",
                "........",
                "........",
                "........",
                "........",
                "pppppppp",
                "rnbkqbnr"));
        historyDTO.setTurn(3);
        assertThat(HistoryDao.getInstance().insertHistory(historyDTO)).isEqualTo(1);
    }
}