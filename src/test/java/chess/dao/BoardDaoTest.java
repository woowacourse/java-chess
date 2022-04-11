package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.BoardDto;
import chess.dto.GameDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private final BoardDao boardDao = new BoardDao();
    private final GameDao gameDao = new GameDao();
    private List<BoardDto> boardDto = new ArrayList<>();
    private int gameId;

    @BeforeEach
    void init() {
        boardDto.add(new BoardDto("PAWN", "WHITE", "a2"));
        gameDao.save(new GameDto("a", "b", "WHITE"));
        gameId = gameDao.findGameIdByUserName("a", "b");
        boardDao.save(boardDto, gameId);
    }

    @AfterEach
    void clear() {
        boardDto.clear();
        gameDao.deleteById(gameId);
    }

    @Test
    void findByGameId() {
        List<BoardDto> boardDtos = boardDao.findByGameId(gameId);
        assertThat(boardDtos.get(0).getSymbol()).isEqualTo("PAWN");
    }
    
    @Test
    void update() {
        BoardDto boardDto = new BoardDto("BISHOP", "WHITE", "a2");
        boardDao.update(boardDto, gameId);

        List<BoardDto> boardDtos = boardDao.findByGameId(gameId);
        assertThat(boardDtos.get(0).getSymbol()).isEqualTo("BISHOP");
    }
}
