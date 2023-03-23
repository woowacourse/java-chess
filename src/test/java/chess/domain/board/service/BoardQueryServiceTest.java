package chess.domain.board.service;

import chess.dao.BoardRegisterDao;
import chess.domain.board.position.Position;
import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.dto.BoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardQueryServiceTest {

    private final BoardRepository boardRepository = new BoardRepository();
    private final BoardMapper boardMapper = new BoardMapper();
    private final BoardQueryService boardQueryService = new BoardQueryService(
            boardRepository, boardMapper
    );

    @BeforeEach
    void initializeData() {
        boardRepository.save(
                new BoardRegisterDao("K : 1 7, P : 3 7, k : 4 7, p : 5 7",
                                     "WHITE")
        );
    }

    @AfterEach
    void clearData() {
        boardRepository.deleteById(1L);
    }

    @Test
    @DisplayName("searchBoard() : board id를 통해 chessBoard와 누구의 차례인지 알 수 있다.")
    void test_searchBoard() throws Exception {
        //given
        final Long boardId = 1L;

        //when
        final BoardSearchResponse boardSearchResponse = boardQueryService.searchBoard(boardId);

        final Map<Position, Piece> chessBoard = boardSearchResponse.chessBoard();
        final String turn = boardSearchResponse.turn();

        //then
        Assertions.assertAll(
                () -> assertEquals(turn, "WHITE"),
                () -> assertThat(chessBoard).hasSize(4)
        );
    }
}
