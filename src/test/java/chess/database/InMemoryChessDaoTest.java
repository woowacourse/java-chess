package chess.database;

import chess.web.ChessCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryChessDaoTest {
    ChessDao chessDao = new InMemoryChessDao();

    @BeforeEach
    void setUp() {
        chessDao.addCommand(new ChessCommand("move a2 a4"));
        chessDao.addCommand(new ChessCommand("move a7 a5"));
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