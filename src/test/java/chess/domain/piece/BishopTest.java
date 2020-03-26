package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BishopTest {
    @Test
    @DisplayName("비숍 생성")
    void constructor() {
        assertThat(new Bishop(Position.from("c1"), Color.WHITE)).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @DisplayName("비숍의 이동 가능한 경로가 존재")
    @CsvSource(value = {"c1,d2", "c1,e3", "c1,a3", "c1,b2"})
    void pathTo(String source, String target) {
        Piece piece = new Bishop(Position.from(source), Color.WHITE);
        assertThat(piece.pathTo(new Blank(Position.from(target)))).isInstanceOf(Path.class);
    }

    @ParameterizedTest
    @DisplayName("비숍의 이동 가능한 경로가 아닌 경우 예외 발생")
    @CsvSource(value = {"c1,d1", "c1,c2", "c7,d7", "c7,e6"})
    void pathTo_invalid_direction(String source, String target) {
        Piece piece = new Bishop(Position.from(source), Color.WHITE);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
            () -> piece.pathTo(new Blank(Position.from(target))));
    }
}
