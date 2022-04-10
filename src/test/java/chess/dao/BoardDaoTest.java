package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.dto.BoardDto;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    private BoardDao boardDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
        gameDao = new GameDao();
    }

    @Test
    void connection() {
        final Connection connection = Connector.getConnection();

        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        gameDao.save("BlackRunning");

        assertThatCode(() -> {
            boardDao.save(List.of(new BoardDto("a1", "ROOK", "BLACK")), gameDao.findGameId());
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void findAll() {
        gameDao.save("WhiteRunning");
        boardDao.save(List.of(new BoardDto("a1", "ROOK", "BLACK")), gameDao.findGameId());
        final List<BoardDto> boardDtos = boardDao.findAll();

        assertThat(boardDtos).isNotEmpty();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void update() {
        gameDao.save("BlackRunning");
        boardDao.save(List.of(new BoardDto("a1", "ROOK", "BLACK")), gameDao.findGameId());

        assertThatCode(() -> {
            boardDao.update(new BoardDto("a1", "PAWN", "WHITE"));
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }
}
