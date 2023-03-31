package chess.dao;

import chess.domain.board.service.dto.BoardModifyRequest;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.dto.BoardSearchResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardDaoTest {

    private final BoardDao boardDao = new BoardDao();

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
        final String position = "King : 1 1, Queen : 1 2";
        final String turn = "WHITE";
        final BoardRegisterRequest boardRegisterRequest = new BoardRegisterRequest(position, turn);

        //when & then
        final Long savedId = boardDao.save(boardRegisterRequest);
        assertEquals(savedId, 3L);
    }

    @Test
    @DisplayName("findById() : board id 를 통해서 조회할 수 있다.")
    void test_findById() throws Exception {
        //given
        final Long boardId = 2L;

        //when
        Optional<BoardSearchResponse> savedBoardDao = boardDao.findById(boardId);

        //then
        assertAll(
                () -> assertTrue(savedBoardDao.isPresent()),
                () -> assertEquals(savedBoardDao.get().position(), "mock data2"),
                () -> assertEquals(savedBoardDao.get().turn(), "BLACK")
        );
    }

    @Test
    @DisplayName("findAll() : 사용자가 참여한 board 를 모두 조회할 수 있다.")
    void test_findAll() throws Exception {
        //when
        List<BoardSearchResponse> boardSearchResponses = boardDao.findAll();

        //then
        assertEquals(2, boardSearchResponses.size());
    }

    @Test
    @DisplayName("modifyById() : board id를 통해서 board를 수정할 수 있다.")
    void test_modifyById() throws Exception {
        //given
        final Long boardId = 2L;
        final String modifyingPosition = "modify data";
        final String turn = "BLACK";

        final BoardModifyRequest boardModifyRequest = new BoardModifyRequest(boardId, modifyingPosition, turn);

        //when
        boardDao.modifyById(boardModifyRequest);
        final Optional<BoardSearchResponse> modifiedBoard = boardDao.findById(boardId);

        //then/
        assertAll(
                () -> assertTrue(modifiedBoard.isPresent()),
                () -> assertEquals(modifiedBoard.get().position(), modifyingPosition),
                () -> assertEquals(modifiedBoard.get().turn(), turn)
        );
    }

    @Test
    @DisplayName("deleteById() : board id를 통해서 board를 삭제할 수 있다.")
    void test_deleteById() throws Exception {
        //given
        final Long boardId = 1L;

        //when
        boardDao.deleteById(boardId);
        final Optional<BoardSearchResponse> savedBoard = boardDao.findById(boardId);

        //then
        assertTrue(savedBoard.isEmpty());
    }
}
