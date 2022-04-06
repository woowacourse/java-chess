package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.BoardInitializer;
import chess.domain.state.State;
import chess.domain.state.White;
import chess.dto.board.BoardDto;
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
}
