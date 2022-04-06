package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.EmblemMapper;
import chess.Game;
import chess.model.Board;
import chess.model.PieceArrangement.DefaultArrangement;

public class BoardDaoImplTest {

    private static BoardDao boardDao;
    private static GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDaoImpl(new Game("white", "black"));
        boardDao = new BoardDaoImpl();
    }


    @AfterEach
    void tearDown() {
        gameDao.deleteById(gameDao.getId());
    }

    @Test
    @DisplayName("Connection 동작을 검증한다.")
    void connection() {

        //when
        Connection connection = boardDao.getConnection();

        //then
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("DB에 현재 기물 위치, 피스 정보를 저장한다.")
    void save() {
        Board board = new Board(new DefaultArrangement());
        gameDao.save();

        assertThatCode(() -> boardDao.save(gameDao.getId(),
            EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues())))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("DB에 존재하는 기물 위치, 피스 정보를 Game id를 기준으로 삭제한다.")
    void deleteById() {
        //given
        Board board = new Board(new DefaultArrangement());
        gameDao.save();
        boardDao.save(gameDao.getId(),
            EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues()));

        //when
        boardDao.deleteById(gameDao.getId());

        //then
        assertThat(boardDao.findById(gameDao.getId())).isEmpty();
    }


    @Test
    @DisplayName("DB에서 Id로 기물 위치, 피스 정보를 불러온다.")
    void find() {
        //given
        GameDao gameDao = new GameDaoImpl(new Game("white", "black"));
        gameDao.save();
        int gameId = gameDao.getId();

        Board board = new Board(new DefaultArrangement());
        Map<String, String> expected = EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues());

        //when
        boardDao.save(gameId, expected);
        Map<String, String> actual = boardDao.findById(gameId);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
