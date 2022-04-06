package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.EmblemMapper;
import chess.model.Board;
import chess.model.PieceArrangement.DefaultArrangement;

public class BoardDaoImplTest {

    private static BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDaoImpl();
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
        assertThatCode(() -> boardDao.save(1,
            EmblemMapper.StringPieceMapByPiecesByPositions(board.getValues())))
            .doesNotThrowAnyException();
    }

}
