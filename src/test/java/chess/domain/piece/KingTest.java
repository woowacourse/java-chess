package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {
    @Test
    @DisplayName("킹 생성")
    void constructor() {
        assertThat(new King(Position.from("e1"), Color.WHITE)).isInstanceOf(King.class);
    }

    @ParameterizedTest
    @DisplayName("킹 이동")
    @CsvSource(value = {"e1,d1", "e1,f1", "e1,f2", "e1,d2", "e8,e7", "d7,d8"})
    void move(String source, String target) {
        Piece piece = new King(Position.from(source), Color.WHITE);
        piece.move(new Blank(Position.from(target)));
        assertThat(piece.getPosition()).isEqualTo(Position.from(target));
    }

    @ParameterizedTest
    @DisplayName("킹이 이동할 수 없는 위치인 경우 예외 발생")
    @CsvSource(value = {"e1,e3", "e1,b2", "e8,e6", "e8,c8"})
    void move_invalid_direction(String source, String target) {
        Piece piece = new King(Position.from(source), Color.WHITE);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
            () -> piece.move(new Blank(Position.from(target))));
    }
}
