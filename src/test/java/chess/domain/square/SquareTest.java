package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        assertThatCode(() -> Square.of(File.A, Rank.FIVE))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이미 존재하는 square에 대해서는 square를 새로 생성하지 않고 앞서 생성된 인스턴스를 가져온다.")
    void get_instance_when_already_exist() {
        Square testSquare = Square.of(File.A, Rank.FIVE);
        assertThat(testSquare).isSameAs(Square.of(File.A, Rank.FIVE));
    }

    @Nested
    @DisplayName("이동 테스트")
    class MoveTest {

        @Test
        @DisplayName("File 범위를 벗어난 경우 이동이 불가능하다.")
        void file_out_of_bound1() {
            final Square square = Square.of(File.A, Rank.THREE);
            assertThatThrownBy(() -> square.move(Direction.LEFT))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("File 범위를 벗어난 경우 이동이 불가능하다.")
        void file_out_of_bound2() {
            final Square square = Square.of(File.H, Rank.THREE);
            assertThatThrownBy(() -> square.move(Direction.RIGHT))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Rank 범위를 벗어난 경우 이동이 불가능하다.")
        void rank_out_of_bound1() {
            final Square square = Square.of(File.C, Rank.ONE);
            assertThatThrownBy(() -> square.move(Direction.DOWN))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Rank 범위를 벗어난 경우 이동이 불가능하다.")
        void rank_out_of_bound2() {
            final Square square = Square.of(File.C, Rank.EIGHT);
            assertThatThrownBy(() -> square.move(Direction.UP))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("같은 칸인지 테스트")
    class SameSquareTest {

        private final Square square = Square.of(File.A, Rank.SIX);

        @Test
        @DisplayName("같은 칸이면 true를 반환한다.")
        void same_square() {
            final Square targetSquare = Square.of(File.A, Rank.SIX);
            assertThat(square.equals(targetSquare)).isTrue();
        }

        @Test
        @DisplayName("같은 칸이 아니라면 false를 반환한다.")
        void not_same_square() {
            final Square targetSquare = Square.of(File.A, Rank.SEVEN);
            assertThat(square.equals(targetSquare)).isFalse();
        }
    }
}
