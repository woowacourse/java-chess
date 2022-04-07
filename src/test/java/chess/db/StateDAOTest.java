package chess.db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class StateDAOTest {

    private static final FakeStatusDAO fakeStatusDAO = new FakeStatusDAO();

    @Test
    void init() {
        fakeStatusDAO.insertId("123");
        assertThat(fakeStatusDAO.isSaved("123")).isTrue();
    }

    @Test
    void get_color_test() {
        fakeStatusDAO.insertId("123");
        assertThat(fakeStatusDAO.getColor("123")).isEqualTo(Color.WHITE);
    }

    @Test
    void convert_color_test() {
        fakeStatusDAO.insertId("123");
        fakeStatusDAO.convertColor("123");
        assertThat(fakeStatusDAO.getColor("123")).isEqualTo(Color.BLACK);
    }

    @AfterEach
    void terminate() {
        fakeStatusDAO.terminate();
    }
}
