package chess.domain.board.service;

import chess.dao.BoardRegisterDao;
import chess.dao.MySqlManager;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.dto.BoardModifyRequest;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.dto.BoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.piece.Color;
import chess.domain.piece.jumper.King;
import chess.domain.piece.pawn.Pawn;
import chess.helper.BoardFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardCommandServiceTest {

    private final BoardRepository boardRepository = new BoardRepository();
    private final BoardMapper boardMapper = new BoardMapper();
    private final BoardCommandService boardCommandService = new BoardCommandService(
            boardRepository, boardMapper
    );
    private final BoardQueryService boardQueryService = new BoardQueryService(
            boardRepository, boardMapper
    );

    @BeforeEach
    void initializeData() {
        boardRepository.save(
                new BoardRegisterDao("K : 1 7, P : 3 7, k : 4 7, p : 5 7",
                                     "WHITE")
        );

        boardRepository.save(
                new BoardRegisterDao("P : 3 7, k : 4 7, p : 5 7",
                                     "BLACK")
        );
    }

    @AfterEach
    void clearData() {
        final String query = "truncate BOARD";

        try (final Connection connection = MySqlManager.establishConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {}
    }

    @Test
    @DisplayName("registerBoard() : board 를 저장할 수 있다.")
    void test_registerBoard() throws Exception {
        //given
        final Board board = new Board(BoardFixture.createBoard());
        final BoardRegisterRequest boardRegisterRequest = new BoardRegisterRequest(board, Color.WHITE);

        //when
        final Long savedId = boardCommandService.registerBoard(boardRegisterRequest);

        //then
        assertEquals(3, savedId);
    }

    @Test
    @DisplayName("modifyBoard() : board 를 수정할 수 있다.")
    void test_modifyBoard() throws Exception {
        //given P : 3 7, k : 4 7, p : 5 7
        final Long boardId = 1L;

        final Board board = new Board(Map.of(
                new Position(3, 7), new Pawn(Color.BLACK),
                new Position(5, 7), new Pawn(Color.BLACK),
                new Position(4, 7), new King(Color.WHITE)
        ));

        final BoardModifyRequest boardModifyRequest = new BoardModifyRequest(boardId, board, Color.BLACK);

        //when
        boardCommandService.modifyBoard(boardModifyRequest);

        final BoardSearchResponse boardSearchResponse = boardQueryService.searchBoard(boardId);

        //then
        assertAll(
                () -> assertEquals(boardSearchResponse.turn(), Color.BLACK.name()),
                () -> assertThat(boardSearchResponse.chessBoard())
                        .hasSize(3)
                        .containsKeys(
                                new Position(3, 7),
                                new Position(5, 7),
                                new Position(4, 7)
                        )
        );
    }

    @Test
    @DisplayName("deleteBoard() : board 를 삭제할 수 있다.")
    void test_deleteBoard() throws Exception {
        //given
        final Long boardId = 2L;

        //when
        boardCommandService.deleteBoard(boardId);

        //then
        assertThatThrownBy(() -> boardQueryService.searchBoard(boardId))
                .isInstanceOf(NoSuchElementException.class);
    }
}
