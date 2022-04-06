package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    void connection() {
        final BoardDao boardDao = new BoardDao();
        final Connection connection = boardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void create() {
        final BoardDao boardDao = new BoardDao();
        boardDao.create(new ChessGameDto("hunch", new ChessGame(new InitBoardStrategy())));
    }

    @Test
    void save() {
        final BoardDao boardDao = new BoardDao();
        boardDao.save(new ChessGameDto("hunch", new ChessGame(new InitBoardStrategy())));
    }

    @Test
    void findByName() {
        final BoardDao boardDao = new BoardDao();
        final ChessGameDto boardDaoByName = boardDao.findByName("hunch");
        assertThat(boardDaoByName.getName()).isEqualTo("hunch");
    }
}
