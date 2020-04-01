package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    @Test
    void of_정상적인_사용() {
        assertThat(Position.of(File.A, Rank.ONE)).isEqualTo(Position.of("a1"));
    }

    @Test
    void of_비정상적인_사용_예외처리() {
        assertThatThrownBy(() -> Position.of("bas"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않는 Position 입니다.");
    }

    @Test
    void canIncrease() {
        assertThat(Position.of("a1").canIncrease(5, 6)).isTrue();
        assertThat(Position.of("a1").canIncrease(-1, 1)).isFalse();

        assertThat(Position.of("a1").canIncrease(File.of(5), Rank.of(6))).isTrue();
    }

    @Test
    void increase_증가시킬수_있는_경우() {
        assertThat(Position.of("a1").increase(2, 5)).isEqualTo(Position.of("c6"));
        assertThat(Position.of("b2").increase(-1, -1)).isEqualTo(Position.of("a1"));
    }

    @Test
    void increase_증가시킬수_없는_경우() {
        assertThatThrownBy(() -> Position.of("a1").increase(-1, -1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void canDecrease() {
        assertThat(Position.of("a1").canDecrease(-5, -6)).isTrue();
        assertThat(Position.of("a1").canDecrease(1, -1)).isFalse();

        assertThat(Position.of("a1").canDecrease(File.of(5), Rank.of(6))).isFalse();
    }

    @Test
    void decrease_감소시킬수_있는_경우() {
        assertThat(Position.of("a1").decrease(-2, -5)).isEqualTo(Position.of("c6"));
        assertThat(Position.of("b2").decrease(1, 1)).isEqualTo(Position.of("a1"));
    }

    @Test
    void decrease_감소시킬수_없는_경우() {
        assertThatThrownBy(() -> Position.of("a1").decrease(1, 1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isOnDiagonalOf() {
        assertThat(Position.of("a3").isOnDiagonalOf(Position.of("c1"))).isTrue();
        assertThat(Position.of("b1").isOnDiagonalOf(Position.of("e4"))).isTrue();
        assertThat(Position.of("g3").isOnDiagonalOf(Position.of("h2"))).isTrue();
        assertThat(Position.of("a3").isOnDiagonalOf(Position.of("c4"))).isFalse();
    }

    @Test
    void isOnStraightOf() {
        assertThat(Position.of("a3").isOnStraightOf(Position.of("a8"))).isTrue();
        assertThat(Position.of("b1").isOnStraightOf(Position.of("h1"))).isTrue();
        assertThat(Position.of("g3").isOnStraightOf(Position.of("h2"))).isFalse();
        assertThat(Position.of("a3").isOnStraightOf(Position.of("c4"))).isFalse();
    }

    @Test
    void calculateXDistance() {
        assertThat(
            Position.of("a3").calculateXDistance(Position.of("b6"))
        ).isEqualTo(1);
    }

    @Test
    void calculateYDistance() {
        assertThat(
            Position.of("a3").calculateYDistance(Position.of("b6"))
        ).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("getPathTestCase")
    void getPath_직선_혹은_대각선_관계인_두_점(Position from, Position to, List<Position> expected) {
        assertThat(Position.getPath(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getPathTestCase() {
        return Stream.of(
            Arguments.of(Position.of("a1"), Position.of("d4"), Arrays.asList(
                Position.of("a1"), Position.of("b2"), Position.of("c3"), Position.of("d4"))),
            Arguments.of(Position.of("h6"), Position.of("d2"),
                Arrays.asList(Position.of("h6"), Position.of("g5"),
                    Position.of("f4"), Position.of("e3"), Position.of("d2"))),
            Arguments.of(Position.of("f6"), Position.of("f2"),
                Arrays.asList(Position.of("f6"), Position.of("f5"),
                    Position.of("f4"), Position.of("f3"), Position.of("f2")))
        );
    }

    @Test
    void getPath_직선_혹은_대각선_관계가_아니면_지원안함() {
        assertThatThrownBy(() -> Position.getPath(Position.of("a1"), Position.of("c2")))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isYLargerThan() {
        assertThat(Position.of("a2").isYLargerThan(Position.of("h1"))).isTrue();
        assertThat(Position.of("h6").isYLargerThan(Position.of("c7"))).isFalse();
    }

    @Test
    void isY() {
        assertThat(Position.of(File.A, Rank.ONE).isY(Rank.ONE)).isTrue();
        assertThat(Position.of(File.A, Rank.ONE).isY(Rank.TWO)).isFalse();
    }

    @Test
    void testToString() {
        assertThat(Position.of(File.G, Rank.SEVEN).toString()).isEqualTo("g7");
    }
}
