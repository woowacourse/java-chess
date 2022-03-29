package chess2.domain2.board2.position;

import static chess2.domain2.board2.position.Direction.LEFT;
import static chess2.domain2.board2.position.Direction.UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PositionTest {

    @Nested
    class OfTest {

        @Test
        void 문자열_키_값에_대응되는_인덱스의_위치_인스턴스를_반환() {
            Position actual = Position.of("c1");

            Position expected = Position.of(2, 0);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 캐쉬_반환() {
            Position actual = Position.of(2, 0);
            Position expected = Position.of(2, 0);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 인덱스가_범위를_벗어나면_예외() {
            assertThatCode(() -> Position.of(8, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 위치입니다. (index 범위: 0~7)");
        }

        @Test
        void 문자열_값에_대응되는_위치가_없으면_예외() {
            assertThatCode(() -> Position.of("z1"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 포지션입니다. (a1~h8)");
        }
    }

    @Nested
    class OneStepTowardTest {

        Position from;

        @BeforeEach
        void setUp() {
             from = Position.of(0, 0);
        }

        @Test
        void 상하() {
            Position to = Position.of(0, 4);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(0, 1);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 좌우() {
            Position to = Position.of(4, 0);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(1, 0);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 대각선() {
            Position to = Position.of(3, 3);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(1, 1);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 일직선이_아니면_대각선으로_이동() {
            Position to = Position.of(1, 2);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(1, 1);

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class CheckDirectionTest {

        @Test
        void 방향이_맞으면_참() {
            Position from = Position.of(0, 0);
            Position to = Position.of(1, 1);

            boolean actual = from.checkDirection(to, UP_RIGHT);

            assertThat(actual).isTrue();
        }

        @Test
        void 방향이_틀리면_거짓() {
            Position from = Position.of(0, 0);
            Position to = Position.of(1, 0);

            boolean actual = from.checkDirection(to, LEFT);

            assertThat(actual).isFalse();
        }
    }
}