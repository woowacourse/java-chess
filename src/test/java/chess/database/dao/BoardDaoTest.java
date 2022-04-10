package chess.database.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import chess.database.dto.BoardDto;
import chess.database.dto.GameStateDto;
import chess.database.dto.PointDto;
import chess.database.dto.RouteDto;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.game.GameState;
import chess.domain.game.Ready;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardDaoTest {

    private static final String TEST_ROOM_NAME = "TESTING";
    private static GameState state;

    private final BoardDao dao = new JdbcBoardDao();

    @BeforeAll
    static void setUp() {
        state = new Ready();
        JdbcGameDao gameDao = new JdbcGameDao();
        gameDao.saveGame(GameStateDto.of(state), TEST_ROOM_NAME);
    }

    @Test
    @Order(1)
    @DisplayName("말의 위치와 종류를 저장한다.")
    public void insert() {
        // given & when
        Board board = Board.of(new InitialBoardGenerator());
        BoardGenerator generator = new InitialBoardGenerator();
        // then
        assertThatCode(() -> dao.saveBoard(BoardDto.of(board.getPointPieces()), TEST_ROOM_NAME))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(2)
    @DisplayName("말의 위치와 종류를 조회한다.")
    public void select() {
        // given
        String roomName = TEST_ROOM_NAME;
        // when
        BoardDto boardDto = dao.readBoard(roomName);
        // then
        assertThat(boardDto.getPointPieces().size()).isEqualTo(32);
    }

    @Test
    @Order(3)
    @DisplayName("존재하지 않는 방을 조회하면 예외를 던진다.")
    public void selectMissingName() {
        // given & when
        String name = "missing";
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> dao.readBoard(name));
    }

    @Test
    @Order(4)
    @DisplayName("말의 위치를 움직인다.")
    public void update() {
        // given & when
        String roomName = TEST_ROOM_NAME;
        Route route = Route.of(List.of("a2", "a4"));
        // then
        assertThatCode(() -> dao.updatePiece(RouteDto.of(route), roomName))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(5)
    @DisplayName("말을 삭제한다.")
    public void delete() {
        // given & when
        String roomName = TEST_ROOM_NAME;
        Point point = Point.of("b2");
        // then
        assertThatCode(() -> dao.deletePiece(PointDto.of(point), roomName))
            .doesNotThrowAnyException();
    }

    @AfterAll
    static void setDown() {
        JdbcConnector.query("DELETE FROM board WHERE room_name = ?")
            .parameters(TEST_ROOM_NAME)
            .executeUpdate();
        JdbcConnector.query("DELETE FROM game WHERE room_name = ?")
            .parameters(TEST_ROOM_NAME)
            .executeUpdate();
    }
}