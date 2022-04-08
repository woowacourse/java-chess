package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.dto.ChessDto;
import chess.util.JdbcTestFixture;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private BoardDao boardDao;
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        dataSource = new TestDataSource();
        boardDao = new BoardDaoImpl(dataSource);
    }

    @Test
    @DisplayName("단건 업데이트 후 보드를 가져온다.")
    void updateAndGet() {
        boardDao.updatePosition("h2", "white_pawn");
        Map<Position, Piece> initialBoard = BoardFactory.initialize();
        initialBoard.put(Position.valueOf("g2"), new Blank());
        Map<String, String> expected = ChessDto.of(new Board(initialBoard)).getBoard();
        assertThat(boardDao.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 보드를 가져온다.")
    void getCurrentBoard() {
        Map<Position, Piece> initialBoard = BoardFactory.initialize();
        initialBoard.put(Position.valueOf("h2"), new Blank());
        initialBoard.put(Position.valueOf("g2"), new Blank());
        Map<String, String> expected = ChessDto.of(new Board(initialBoard)).getBoard();
        assertThat(boardDao.getBoard()).isEqualTo(expected);
    }

    @Test
    @DisplayName("배치 업데이트 후 보드를 가져온다.")
    void updateBatchAndGet() throws SQLException {
        Map<String, String> map = new HashMap<>();

        map.put("g2", "white_pawn");
        map.put("h2", "white_pawn");
        boardDao.updateBatchPositions(map);

        Map<String, String> expected = ChessDto.of(new Board(BoardFactory.initialize())).getBoard();
        assertThat(boardDao.getBoard()).isEqualTo(expected);
    }

    @AfterEach
    void tearDown() {
        boardDao.updateBatchPositions(JdbcTestFixture.getTestBoard());
    }

}
