package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.BoardInitializer;
import chess.domain.state.State;
import chess.domain.state.White;
import chess.dto.board.BoardDto;
import chess.dto.board.BoardInformationDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    private BoardDto boardDto;
    private Connection connection;
    private BoardDao boardDao;

    @BeforeEach
    void set() throws SQLException {
        final State state = new White(new BoardInitializer().init());
        boardDto = BoardDto.of(state);
        connection = MysqlConnector.connect();
        boardDao = new BoardDao();
        connection.setAutoCommit(false);
    }

    @DisplayName("체스판 저장 테스트")
    @Test
    void save(){
        String turn = boardDto.getTurn();;

        assertDoesNotThrow(() -> boardDao.save(turn, MysqlConnector.connect()));
    }

    @DisplayName("가장 최근에 저장된 게임 불러오기 테스트")
    @Test
    void findRecentBoard1(){
        assertDoesNotThrow(() -> boardDao.findRecentBoard(MysqlConnector.connect()));
    }

    @DisplayName("가장 최근에 저장된 게임 불러오기 테스트")
    @Test
    void findRecentBoard2() throws SQLException {
        String turn = boardDto.getTurn();
        int recentBoardId = boardDao.save(turn, MysqlConnector.connect());
        BoardInformationDto dto = boardDao.findRecentBoard(MysqlConnector.connect());

        assertThat(dto.getId()).isEqualTo(recentBoardId);
    }
}
