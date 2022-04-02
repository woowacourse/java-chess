package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTest {
    @Test
    @DisplayName("같은 파일과 같은 랭크를 가진 스퀘어들은 동등하다")
    void equals() {
        Square square1 = new Square("h3");
        Square square2 = new Square("h3");
        assertThat(square1.equals(square2)).isTrue();
    }

    @Test
    @DisplayName("String으로 생성된 스퀘어가 올바른 파일과 랭크를 가진다")
    void makeSquareFromString() {
        assertThat(new Square("h3")).isEqualTo(new Square(Column.H, Row.THREE));
    }
}
