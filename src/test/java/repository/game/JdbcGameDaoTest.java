package repository.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.BoardDto;
import dto.MoveHistoryDto;
import repository.TestConnector;
import repository.connector.JdbcConnector;
import repository.room.JdbcRoomDao;

class JdbcGameDaoTest {

    JdbcConnector connector = new TestConnector();
    JdbcRoomDao jdbcChessDao = new JdbcRoomDao(connector);
    JdbcGameDao jdbcGameDao = new JdbcGameDao(connector);

    @Test
    @DisplayName("saveMoveHistory를 통해 move를 저장한다.")
    void saveMoveHistory() {
        long gameId = jdbcChessDao.createRoom("테스트");
        MoveHistoryDto moveHistoryDto = new MoveHistoryDto("a2", "a3", "EMPTY");
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto);

        List<MoveHistoryDto> moveHistoryByGameId = jdbcGameDao.findMoveHistoryByGameId(gameId);

        assertThat(moveHistoryByGameId).contains(moveHistoryDto);
    }

    @Test
    @DisplayName("saveBoard를 통해 현재보드를 저장한다.")
    void saveBoard() {
        long gameId = jdbcChessDao.createRoom("테스트");
        List<BoardDto> boardDtos = List.of(
                new BoardDto("a2", "PAWN", "WHITE"),
                new BoardDto("a3", "QUEEN", "BLACK"),
                new BoardDto("a4", "KNIGHT", "BLACK"),
                new BoardDto("a5", "EMPTY", "NONE"),
                new BoardDto("a6", "KING", "WHITE"),
                new BoardDto("a7", "KING", "BLACK")
        );

        jdbcGameDao.saveBoard(gameId, boardDtos);
        List<BoardDto> boardDto = jdbcGameDao.findBoardByRoomId(gameId);

        assertThat(boardDto).containsExactlyInAnyOrderElementsOf(boardDto);
    }

    @Test
    @DisplayName("gameId를 전달 받아 해당하는 board를 삭제한다.")
    void deleteBoardById() {
        long gameId = jdbcChessDao.createRoom("테스트");
        List<BoardDto> boardDtos = List.of(
                new BoardDto("a2", "PAWN", "WHITE"),
                new BoardDto("a3", "QUEEN", "BLACK"),
                new BoardDto("a4", "KNIGHT", "BLACK"),
                new BoardDto("a5", "EMPTY", "NONE"),
                new BoardDto("a6", "KING", "WHITE"),
                new BoardDto("a7", "KING", "BLACK")
        );
        jdbcGameDao.saveBoard(gameId, boardDtos);

        jdbcGameDao.deleteBoardById(gameId);

        List<BoardDto> boardDto = jdbcGameDao.findBoardByRoomId(gameId);
        assertThat(boardDto).isEmpty();
    }
}
