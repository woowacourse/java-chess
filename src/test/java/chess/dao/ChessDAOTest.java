package chess.dao;

import chess.model.board.BoardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessDAOTest {
    private ChessDAO dao;

    @BeforeEach
    void setUp() {
        dao = ChessDAO.getInstance();
    }

    @Test
    void deleteAll() throws SQLException {
        dao.deleteAll();
        int turn = dao.getLatestTurn();
        assertThat(dao.selectByTurn(turn)).isEqualTo(new BoardDTO(Collections.emptyList()));
    }

    @Test
    void insert_init() throws SQLException {
        dao.deleteAll();
        dao.insertInit();
        assertThat(dao.selectByTurn(1)).isEqualTo(new BoardDTO(
                Arrays.asList(
                        "RNBQKBNR",
                        "PPPPPPPP",
                        "########",
                        "########",
                        "########",
                        "########",
                        "pppppppp",
                        "rnbqkbnr")));
        assertThat(dao.getLatestTurn()).isEqualTo(1);
    }

    @Test
    void select_by_turn() throws SQLException {
        dao.deleteAll();
        dao.insertInit();
        assertThat(dao.selectByTurn(1)).isEqualTo(new BoardDTO(
                Arrays.asList(
                        "RNBQKBNR",
                        "PPPPPPPP",
                        "########",
                        "########",
                        "########",
                        "########",
                        "pppppppp",
                        "rnbqkbnr")));
    }

    @Test
    void update_board() throws SQLException {
        BoardDTO dto = new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "p#######",
                "########",
                "#ppppppp",
                "rnbqkbnr"));
        dao.deleteAll();
        dao.insertInit();
        dao.updateBoard(dto);
        assertThat(dao.selectByTurn(2)).isEqualTo(new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "p#######",
                "########",
                "#ppppppp",
                "rnbqkbnr")));
    }

    @Test
    void db에서_마지막_턴_값_가져오는지_확인() throws SQLException {
        dao.deleteAll();
        dao.insertInit();
        dao.updateBoard(new BoardDTO(Arrays.asList(
                "RNBQKBNR",
                "PPPPPPPP",
                "########",
                "########",
                "p#######",
                "########",
                "#ppppppp",
                "rnbqkbnr")));
        assertThat(dao.getLatestTurn()).isEqualTo(2);
    }
}
