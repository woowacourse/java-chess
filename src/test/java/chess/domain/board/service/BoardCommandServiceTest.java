package chess.domain.board.service;

import chess.dao.BoardRegisterDao;
import chess.domain.board.Board;
import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.piece.Color;
import chess.helper.BoardFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardCommandServiceTest {

    private final BoardRepository boardRepository = new BoardRepository();
    private final BoardMapper boardMapper = new BoardMapper();
    private final BoardCommandService boardCommandService = new BoardCommandService(
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
}
