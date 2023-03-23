package chess.domain.board.repository;

import chess.dao.BoardModifyDao;
import chess.dao.BoardRegisterDao;
import chess.dao.BoardSearchDao;
import chess.dao.MySqlManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardRepositoryTest {

    private final BoardRepository boardRepository = new BoardRepository();

    @BeforeEach
    void initDatabase() {
        final String query = "INSERT INTO BOARD(POSITION, TURN) VALUES(?, ?)";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, "mock data1");
            preparedStatement.setString(2, "WHITE");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {}

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1, "mock data2");
            preparedStatement.setString(2, "BLACK");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {}
    }

    @AfterEach
    void clearDatabase() {
        final String query = "truncate BOARD";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {}
    }

    @Test
    @DisplayName("save() : board를 저장할 수 있다.")
    void test_save() throws Exception {
        //given
        final String position = "King : 1 1, Queen : 1, 2";
        final String turn = "WHITE";
        final BoardRegisterDao boardRegisterDao = new BoardRegisterDao(position, turn);

        //when & then
        Assertions.assertDoesNotThrow(() -> boardRepository.save(boardRegisterDao));
    }

    @Test
    @DisplayName("findById() : board id 를 통해서 조회할 수 있다.")
    void test_findById() throws Exception {
        //given
        final Long boardId = 2L;

        //when
        Optional<BoardSearchDao> savedBoardDao = boardRepository.findById(boardId);

        //then
        assertAll(
                () -> assertTrue(savedBoardDao.isPresent()),
                () -> assertEquals(savedBoardDao.get().position(), "mock data2"),
                () -> assertEquals(savedBoardDao.get().turn(), "BLACK")
        );
    }

    @Test
    @DisplayName("modifyById() : board id를 통해서 board를 수정할 수 있다.")
    void test_modifyById() throws Exception {
        //given
        final Long boardId = 2L;
        final String modifyingPosition = "modify data";
        final String turn = "BLACK";

        final BoardModifyDao boardModifyDao = new BoardModifyDao(boardId, modifyingPosition, turn);

        //when
        boardRepository.modifyById(boardModifyDao);
        final Optional<BoardSearchDao> modifiedBoardDao = boardRepository.findById(boardId);

        //then
        assertAll(
                () -> assertTrue(modifiedBoardDao.isPresent()),
                () -> assertEquals(modifiedBoardDao.get().position(), modifyingPosition),
                () -> assertEquals(modifiedBoardDao.get().turn(), turn)
        );
    }

    @Test
    @DisplayName("deleteById() : board id를 통해서 board를 삭제할 수 있다.")
    void test_deleteById() throws Exception {
        //given
        final Long boardId = 1L;

        //when
        boardRepository.deleteById(boardId);
        final Optional<BoardSearchDao> savedBoardDao = boardRepository.findById(boardId);

        //then
        assertTrue(savedBoardDao.isEmpty());
    }
}
