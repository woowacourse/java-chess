package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.abstractPiece.Piece;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {
    private static final String ROOM_NAME = "roomName";
    private BoardDao boardDao;
    private static Connection connection;

    @BeforeAll
    static void openConnection() {
        try {
            ConnectionGenerator connectionGenerator = new TestConnectionGenerator();
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException ignored) {
        }
    }

    @AfterAll
    static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
    }

    @AfterEach
    void tearDown() {
        try {
            connection.rollback();
        } catch (SQLException ignored) {
        }
    }


    @DisplayName("보드 기물을 모두 저장한다.")
    @Test
    void addAll() {
        Board board = new Board(Map.of(Position.of(1, 1), new WhitePawn()));

        boardDao.addAll(board, ROOM_NAME, connection);

        assertThat(boardDao.loadAll(ROOM_NAME, connection).getPieces()).isEqualTo(board.getPieces());
    }

    @DisplayName("존재하는 게임의 기물들을 반환한다.")
    @Test
    void loadAll() {
        assertThat(boardDao.loadAll(ROOM_NAME, connection).getPieces()).isEmpty();
    }

    @DisplayName("보드 기물의 변화를 저장한다.")
    @Test
    void update() {
        Piece piece = new WhitePawn();
        Board board = new Board(Map.of(Position.of(1, 1), piece));

        boardDao.addAll(board, ROOM_NAME, connection);
        boardDao.update(new Movement(Position.of(1, 1), Position.of(3, 1)),
                piece, ROOM_NAME, connection);

        assertThat(boardDao.loadAll(ROOM_NAME, connection).getPieces())
                .containsExactly(Map.entry(Position.of(3, 1), piece));
    }

    @DisplayName("같은 방 이름을 가진 모든 기물을 제거한다.")
    @Test
    void delete() {
        Board board = new Board(Map.of(Position.of(1, 1), new WhitePawn()));
        boardDao.addAll(board, ROOM_NAME, connection);
        boardDao.delete(ROOM_NAME, connection);
        assertThat(boardDao.loadAll(ROOM_NAME, connection).getPieces()).isEmpty();
    }
}
