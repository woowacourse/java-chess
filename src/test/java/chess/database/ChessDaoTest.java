package chess.database;

import chess.dao.ChessDao;
import chess.dao.InMemoryChessDao;
import chess.dao.MySqlChessDao;
import chess.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDaoTest {

    ChessDao chessDao = new InMemoryChessDao();

    @BeforeEach
    void setUp() {
        if (MySqlConnector.getConnection() != null) {
            chessDao = new MySqlChessDao();
        }
        chessDao.addCommand(new CommandDto("move a2 a4"));
        chessDao.addCommand(new CommandDto("move a7 a5"));
    }

    @DisplayName("테이블 행 삭제")
    @Test
    void clearCommands() {
        chessDao.clearCommands();

        assertThat(chessDao.selectCommands().size()).isZero();
    }

    @DisplayName("전체 행 선택")
    @Test
    void selectCommands() {
        assertThat(chessDao.selectCommands().size()).isEqualTo(2);
    }
}