package chess.repository.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.repository.TestConnector;
import dto.BoardDto;
import dto.MoveHistoryDto;
import repository.connector.JdbcConnector;
import repository.game.JdbcGameDao;
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

    @Test
    @DisplayName("gameId를 전달 받아 가장 마지막에 해당하는 moveHistory 2개를 가져온다.")
    void findLastTwoMoveHistories() {
        long gameId = jdbcChessDao.createRoom("테스트");
        MoveHistoryDto moveHistoryDto1 = new MoveHistoryDto("a2", "a3", "EMPTY");
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto1);
        MoveHistoryDto moveHistoryDto2 = new MoveHistoryDto("a1", "a6", "PAWN");
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto2);
        MoveHistoryDto moveHistoryDto3 = new MoveHistoryDto("b7", "b3", "KNIGHT");
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto3);

        List<MoveHistoryDto> moveHistoryDtos = jdbcGameDao.findLastTwoMoveHistories(gameId);

        for (MoveHistoryDto moveHistoryDto : moveHistoryDtos) {
            System.out.println(moveHistoryDto.getPiece());
        }
        assertThat(moveHistoryDtos).containsExactly(moveHistoryDto3, moveHistoryDto2);
    }

    @Test
    @DisplayName("gameId를 전달 받아 해당 게임에서 가장 최근 2개의 moveHistory를 삭제한다.")
    void deleteLatestTwoMoveHistory() {
        long gameId = jdbcChessDao.createRoom("테스트");
        MoveHistoryDto moveHistoryDto1 = new MoveHistoryDto("a2", "a3", "EMPTY");
        MoveHistoryDto moveHistoryDto2 = new MoveHistoryDto("a1", "a6", "PAWN");
        MoveHistoryDto moveHistoryDto3 = new MoveHistoryDto("b7", "b3", "KNIGHT");
        MoveHistoryDto moveHistoryDto4 = new MoveHistoryDto("c7", "d3", "KING");
        MoveHistoryDto moveHistoryDto5 = new MoveHistoryDto("g7", "h7", "QUEEN");
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto1);
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto2);
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto3);
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto4);
        jdbcGameDao.saveMoveHistory(gameId, moveHistoryDto5);

        jdbcGameDao.deleteLatestTwoHistory(gameId);
        List<MoveHistoryDto> moveHistoryByGameId = jdbcGameDao.findMoveHistoryByGameId(gameId);

        assertThat(moveHistoryByGameId).doesNotContain(moveHistoryDto4, moveHistoryDto5);
    }
}
