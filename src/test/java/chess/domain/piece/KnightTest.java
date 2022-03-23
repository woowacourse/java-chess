package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("나이트 기물 생성")
    void createPawn(Color color) {
        Piece knight = new Knight(color, new Position('a', '1'));
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2", "a,3", "a,4", "b,1", "b,2", "c,3"})
    @DisplayName("나이트 위치 이동 불가 예외발생")
    void moveException(char row, char col) {
        Knight knight = new Knight(Color.WHITE, new Position('a', '1'));
        Position movePosition = new Position(row, col);

        assertThatThrownBy(() -> knight.move(movePosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("나이트는 L자 형태로만 움직일 수 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3", "c,2"})
    @DisplayName("나이트 위치 이동")
    void move(char row, char col) {
        Knight knight = new Knight(Color.WHITE, new Position('a', '1'));
        Position expected = new Position(row, col);

        Knight actual = knight.move(expected);
        assertThat(actual.getPosition()).isEqualTo(expected);
    }
}
