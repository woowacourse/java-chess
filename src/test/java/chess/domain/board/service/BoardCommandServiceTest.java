package chess.domain.board.service;

import chess.dao.BoardRegisterDao;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
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
    }

    @AfterEach
    void clearData() {
        boardRepository.deleteById(1L);
    }

    @Test
    @DisplayName("registerBoard() : board 를 저장할 수 있다.")
    void test_registerBoard() throws Exception {
        //given
        final Board board = new Board(BoardFixture.createBoard());
        final BoardRegisterRequest boardRegisterRequest = new BoardRegisterRequest(board, Color.WHITE);

        //when & then
        Assertions.assertDoesNotThrow(() -> boardCommandService.registerBoard(boardRegisterRequest));
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

        final BoardSearchResponse boardSearchResponse = boardQueryService.searchBoard(1L);

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
}
