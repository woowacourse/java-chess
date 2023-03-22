package chess.domain.square;

import chess.domain.exception.FileNotFoundException;
import chess.domain.exception.RankNotFoundException;
import chess.domain.exception.SquareOutOfBoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("같은 File, Rank 테스트")
    class SameRank {

        private final Square square = Square.of(File.A, Rank.TWO);

        @Test
        @DisplayName("같은 File이면 true를 반환한다.")
        void same_file() {
            final Square sameFileSquare = Square.of(File.A, Rank.THREE);

            assertThat(square.isSameFile(sameFileSquare)).isTrue();
        }

        @Test
        @DisplayName("같은 File이 아니면 false를 반환한다.")
        void not_same_file() {
            final Square notSameFileSquare = Square.of(File.B, Rank.FIVE);

            assertThat(square.isSameFile(notSameFileSquare)).isFalse();
        }

        @Test
        @DisplayName("같은 Rank이면 true를 반환한다.")
        void same_rank() {
            final Square sameRankSquare = Square.of(File.B, Rank.TWO);

            assertThat(square.isSameRank(sameRankSquare)).isTrue();
        }

        @Test
        @DisplayName("같은 Rank가 아니면 false를 반환한다.")
        void not_same_rank() {
            final Square notSameRankSquare = Square.of(File.B, Rank.FIVE);

            assertThat(square.isSameRank(notSameRankSquare)).isFalse();
        }
    }

    @Nested
    @DisplayName("진행방향 테스트")
    class DirectionTest {

        private final Square square = Square.of(File.A, Rank.TWO);

        @Test
        @DisplayName("검사하려는 칸이 더 큰 Rank인 경우")
        void bigger_rank() {
            final Square target = Square.of(File.A, Rank.THREE);
            assertThat(square.isRankBiggerThan(target)).isFalse();
            assertThat(square.isRankLowerThan(target)).isTrue();
        }

        @Test
        @DisplayName("검사하려는 칸이 더 작은 Rank인 경우")
        void lower_rank() {
            final Square target = Square.of(File.A, Rank.ONE);
            assertThat(square.isRankBiggerThan(target)).isTrue();
            assertThat(square.isRankLowerThan(target)).isFalse();
        }
    }

    @Nested
    @DisplayName("Rank 차이 테스트")
    class DifferenceTest {

        private final Square square = Square.of(File.A, Rank.FIVE);

        @Test
        @DisplayName("차이가 1이면 true를 반환한다.")
        void difference_is_one() {
            final Square target = Square.of(File.A, Rank.SIX);
            assertThat(square.isRankDifferenceOne(target)).isTrue();
        }

        @Test
        @DisplayName("차이가 1이 아니면 false를 반환한다.")
        void difference_is_not_one() {
            final Square target = Square.of(File.A, Rank.SEVEN);
            assertThat(square.isRankDifferenceOne(target)).isFalse();
        }
    }

    @Nested
    @DisplayName("이동 테스트")
    class MoveTest {

        @Test
        @DisplayName("File 범위를 벗어난 경우 이동이 불가능하다.")
        void file_out_of_bound1() {
            final Square square = Square.of(File.A, Rank.THREE);
            assertThatThrownBy(() -> square.move(Direction.LEFT))
                    .isInstanceOf(SquareOutOfBoundException.class);
        }

        @Test
        @DisplayName("File 범위를 벗어난 경우 이동이 불가능하다.")
        void file_out_of_bound2() {
            final Square square = Square.of(File.H, Rank.THREE);
            assertThatThrownBy(() -> square.move(Direction.RIGHT))
                    .isInstanceOf(SquareOutOfBoundException.class);
        }

        @Test
        @DisplayName("Rank 범위를 벗어난 경우 이동이 불가능하다.")
        void rank_out_of_bound1() {
            final Square square = Square.of(File.C, Rank.ONE);
            assertThatThrownBy(() -> square.move(Direction.DOWN))
                    .isInstanceOf(SquareOutOfBoundException.class);
        }

        @Test
        @DisplayName("Rank 범위를 벗어난 경우 이동이 불가능하다.")
        void rank_out_of_bound2() {
            final Square square = Square.of(File.C, Rank.EIGHT);
            assertThatThrownBy(() -> square.move(Direction.UP))
                    .isInstanceOf(SquareOutOfBoundException.class);
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