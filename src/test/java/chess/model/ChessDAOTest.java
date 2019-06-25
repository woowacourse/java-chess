package chess.model;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessDAOTest {
    //select test

    @Test
    void select_by_turn_test() throws SQLException {
        ChessDAO dao = ChessDAO.getInstance();
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
}
