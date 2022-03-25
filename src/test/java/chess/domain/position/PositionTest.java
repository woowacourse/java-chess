package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    @DisplayName("fileIdx와 rankIdx를 받는 of 메서드는 대응되는 문자열로 받는 of 메서드와 동일한 인스턴스를 반환한다.")
    @Test
    void of() {
        Position actual = Position.of(2, 0);

        Position expected = Position.of("c1");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("동일한 위치 정보로 위치 인스턴스를 생성하는 경우, 캐쉬된 위치 인스턴스가 반환된다.")
    @Test
    void of_returnsCache() {
        Position actual = Position.of("a1");
        Position expected = Position.of("a1");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("범위를 벗어한 위치 정보를 입력하는 경우 예외가 발생한다.")
    @Test
    void of_exceptionOnInvalidRange() {
        assertThatCode(() -> Position.of("z0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 포지션입니다. (a1~h8)");
    }

    @DisplayName("positionsBetween 메서드는 대상 위치 사이에 존재하는 위치들의 리스트를 반환한다.")
    @Nested
    class PositionsBetweenTest {

        @Test
        void 상하() {
            Position curPosition = Position.of(4, 4);
            Position targetPosition = Position.of(4, 0);

            List<Position> actual = curPosition.positionsBetween(targetPosition);
            List<Position> expected = List.of(
                    Position.of(4, 3),
                    Position.of(4, 2),
                    Position.of(4, 1));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 좌우() {
            Position curPosition = Position.of(4, 4);
            Position targetPosition = Position.of(0, 4);

            List<Position> actual = curPosition.positionsBetween(targetPosition);
            List<Position> expected = List.of(
                    Position.of(3, 4),
                    Position.of(2, 4),
                    Position.of(1, 4));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 대각선() {
            Position curPosition = Position.of(0, 0);
            Position targetPosition = Position.of(4, 4);

            List<Position> actual = curPosition.positionsBetween(targetPosition);
            List<Position> expected = List.of(
                    Position.of(1, 1),
                    Position.of(2, 2),
                    Position.of(3, 3));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 인접하면_빈_리스트() {
            Position curPosition = Position.of(4, 4);
            Position targetPosition = Position.of(4, 3);

            List<Position> actual = curPosition.positionsBetween(targetPosition);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 직선이_아니면_빈_리스트() {
            Position curPosition = Position.of(0, 0);
            Position targetPosition = Position.of(1, 2);

            List<Position> actual = curPosition.positionsBetween(targetPosition);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }
    }
}
