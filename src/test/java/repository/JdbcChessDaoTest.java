package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.BoardDto;
import dto.MoveHistoryDto;

class JdbcChessDaoTest {
    JdbcConnector connector = new TestConnector();
    JdbcChessDao jdbcChessDao = new JdbcChessDao(connector);

    @BeforeEach
    void setUp() {
        jdbcChessDao.deleteAllMoveHistory();
        jdbcChessDao.deleteAllBoard();
        jdbcChessDao.deleteAllGame();
    }

    @Test
    @DisplayName("DB 커넥션 테스트")
    void connection() throws SQLException {
        try (Connection connection = connector.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    @DisplayName("addGame을 통해 DB에 게임을 저장한다.")
    void addGame() {
        jdbcChessDao.addGame("테스트");

        List<String> allGame = jdbcChessDao.findAllGame();

        assertThat(allGame).contains("테스트");
    }

    @Test
    @DisplayName("saveMoveHistory를 통해 move를 저장한다.")
    void saveMoveHistory() {
        long gameId = jdbcChessDao.addGame("테스트");
        MoveHistoryDto moveHistoryDto = new MoveHistoryDto("a2", "a3", "EMPTY");
        jdbcChessDao.saveMoveHistory(gameId, moveHistoryDto);

        List<MoveHistoryDto> moveHistoryByGameId = jdbcChessDao.findMoveHistoryByGameId(gameId);

        assertThat(moveHistoryByGameId).contains(moveHistoryDto);
    }

    @Test
    @DisplayName("saveBoard를 통해 현재보드를 저장한다.")
    void saveBoard() {
        long gameId = jdbcChessDao.addGame("테스트");
        List<BoardDto> boardDtos = List.of(
                new BoardDto("a2", "PAWN", "WHITE"),
                new BoardDto("a3", "QUEEN", "BLACK"),
                new BoardDto("a4", "KNIGHT", "BLACK"),
                new BoardDto("a5", "EMPTY", "NONE"),
                new BoardDto("a6", "KING", "WHITE"),
                new BoardDto("a7", "KING", "BLACK")
        );

        jdbcChessDao.saveBoard(gameId, boardDtos);
        List<BoardDto> boardDto = jdbcChessDao.findBoardByGameName("테스트");

        assertThat(boardDto).containsExactlyInAnyOrderElementsOf(boardDto);
    }

    @Test
    @DisplayName("gameId를 전달 받아 해당하는 board를 삭제한다.")
    void deleteBoardById() {
        long gameId = jdbcChessDao.addGame("테스트");
        List<BoardDto> boardDtos = List.of(
                new BoardDto("a2", "PAWN", "WHITE"),
                new BoardDto("a3", "QUEEN", "BLACK"),
                new BoardDto("a4", "KNIGHT", "BLACK"),
                new BoardDto("a5", "EMPTY", "NONE"),
                new BoardDto("a6", "KING", "WHITE"),
                new BoardDto("a7", "KING", "BLACK")
        );
        jdbcChessDao.saveBoard(gameId, boardDtos);

        jdbcChessDao.deleteBoardById(gameId);

        List<BoardDto> boardDto = jdbcChessDao.findBoardByGameName("테스트");
        assertThat(boardDto).isEmpty();
    }
}
