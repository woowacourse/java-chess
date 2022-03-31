package chess.domain.board.position;

import static chess.domain.board.position.Direction.LEFT;
import static chess.domain.board.position.Direction.UP_RIGHT;
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

            Position expected = Position.of(File.C, Rank.ONE);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 캐쉬_반환() {
            Position actual = Position.of(File.C,Rank.ONE);
            Position expected = Position.of(File.C, Rank.ONE);

            assertThat(actual).isEqualTo(expected);
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
             from = Position.of(File.A, Rank.ONE);
        }

        @Test
        void 상하() {
            Position to = Position.of(File.A, Rank.FIVE);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(File.A, Rank.TWO);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 좌우() {
            Position to = Position.of(File.E, Rank.ONE);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(File.B, Rank.ONE);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 대각선() {
            Position to = Position.of(File.D, Rank.FOUR);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(File.B, Rank.TWO);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 일직선이_아니면_대각선으로_이동() {
            Position to = Position.of(File.B, Rank.THREE);

            Position actual = from.oneStepToward(to);
            Position expected = Position.of(File.B, Rank.TWO);

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class CheckDirectionTest {

        @Test
        void 방향이_맞으면_참() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.TWO);

            boolean actual = from.checkDirection(to, UP_RIGHT);

            assertThat(actual).isTrue();
        }

        @Test
        void 방향이_틀리면_거짓() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.ONE);

            boolean actual = from.checkDirection(to, LEFT);

            assertThat(actual).isFalse();
        }
    }
}