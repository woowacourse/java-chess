package chess.domain.board.service;

import chess.dao.BoardDao;
import chess.dao.MySqlManager;
import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.board.position.Position;
import chess.domain.board.service.dto.AllBoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardQueryServiceTest {

    private final BoardDao boardDao = new BoardDao();
    private final BoardMapper boardMapper = new BoardMapper();
    private final BoardQueryService boardQueryService = new BoardQueryService(
            boardDao, boardMapper
    );

    @BeforeEach
    void initializeData() {
        boardDao.save(
                new BoardRegisterRequest("K : 1 7, P : 3 7, k : 4 7, p : 5 7",
                                         "WHITE")
        );

        boardDao.save(
                new BoardRegisterRequest("K : 1 7, P : 3 7, k : 4 7, p : 5 7",
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
    @DisplayName("searchBoard() : board id를 통해 chessBoard와 누구의 차례인지 알 수 있다.")
    void test_searchBoard() throws Exception {
        //given
        final Long boardId = 1L;

        //when
        final Board board = boardQueryService.searchBoard(boardId);

        final Map<Position, Piece> chessBoard = board.chessBoard();
        final Turn turn = board.turn();

        //then
        Assertions.assertAll(
                () -> assertEquals(turn, new Turn(Color.WHITE)),
                () -> assertThat(chessBoard).hasSize(4)
        );
    }

    @Test
    @DisplayName("searchAllBoards() : 사용자가 참여하고 있는 모든 Board를 조회할 수 있다.")
    void test_searchAllBoards() throws Exception {
        //when
        final AllBoardSearchResponse allBoardSearchResponse = boardQueryService.searchAllBoards();

        //then
        assertThat(allBoardSearchResponse.ids()).hasSize(2);
    }
}
