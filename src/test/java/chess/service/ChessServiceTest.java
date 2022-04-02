package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.dao.BoardDaoImpl;
import chess.dao.FakeBoardDao;
import chess.dao.FakeTurnDao;
import chess.dao.TurnDaoImpl;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessDto;
import chess.util.JdbcTemplate;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private ChessService chessService;
    private ChessService mockChessService;

    @BeforeEach
    void setUp() {
        Connection connection = JdbcTemplate.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chessService = new ChessService(new BoardDaoImpl(connection), new TurnDaoImpl(connection));
        mockChessService = new ChessService(new FakeBoardDao(), new FakeTurnDao());
    }

    @Test
    @DisplayName("게임이 종료되면 원래대로 돌아온다.")
    void endGame() {
        ChessDto chessDto = chessService.endGame();
        ChessDto expected = ChessDto.of(new Board(BoardFactory.initialize()));

        assertAll(
                () -> assertThat(chessDto.getGameOver()).isEqualTo("true"),
                () -> assertThat(chessDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessDto.getBoard()).isEqualTo(expected.getBoard())
        );
    }

    @Test
    @DisplayName("게임이 실행되면 저장된 턴, 보드값을 가져온다.")
    void startGame() {
        ChessDto chessDto = mockChessService.initializeGame();
        Map<Position, Piece> expected = FakeBoardDao.getPositionPieceMap();

        assertAll(
                () -> assertThat(chessDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessDto.getBoard()).isEqualTo(ChessDto.of(new Board(expected)).getBoard())
        );
    }

    @AfterEach
    void teardown() {
        Connection connection = JdbcTemplate.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}