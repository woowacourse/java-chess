package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.Game;
import chess.MappingUtil;
import chess.model.Board;
import chess.model.PieceArrangement.DefaultArrangement;

public class BoardDaoImplTest {

    private static final BoardDao boardDao = new BoardDaoImpl();
    private static final GameDao gameDao = new GameDaoImpl();
    private static final int gameId = 1;

    @AfterEach
    void tearDown() {
        gameDao.deleteById(gameId);
    }

    @Test
    @DisplayName("DB에 현재 기물 위치, 피스 정보를 저장한다.")
    void save() {
        Board board = new Board(new DefaultArrangement());
        gameDao.save(new Game("white", "black", gameId));

        assertThatCode(() -> boardDao.save(1,
            MappingUtil.StringPieceMapByPiecesByPositions(board.getValues())))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("DB에 존재하는 기물 위치, 피스 정보를 Game id를 기준으로 삭제한다.")
    void deleteById() {
        //given
        Board board = new Board(new DefaultArrangement());
        gameDao.save(new Game("white", "black", gameId));
        boardDao.save(1,
            MappingUtil.StringPieceMapByPiecesByPositions(board.getValues()));

        //when
        boardDao.deleteById(1);

        //then
        assertThat(boardDao.findById(1)).isEmpty();
    }

    @Test
    @DisplayName("DB에서 Id로 기물 위치, 피스 정보를 불러온다.")
    void find() {
        //given
        GameDao gameDao = new GameDaoImpl();
        gameDao.save(new Game("white", "black", gameId));
        int gameId = 1;

        Board board = new Board(new DefaultArrangement());
        Map<String, String> expected = MappingUtil.StringPieceMapByPiecesByPositions(board.getValues());

        //when
        boardDao.save(gameId, expected);
        Map<String, String> actual = boardDao.findById(gameId);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
