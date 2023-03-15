package chess.domain.square;

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
            final var sameFileSquare = Square.of(File.A, Rank.THREE);

            assertThat(square.isSameFile(sameFileSquare)).isTrue();
        }

        @Test
        @DisplayName("같은 File이 아니면 false를 반환한다.")
        void not_same_file() {
            final var notSameFileSquare = Square.of(File.B, Rank.FIVE);

            assertThat(square.isSameFile(notSameFileSquare)).isFalse();
        }

        @Test
        @DisplayName("같은 Rank이면 true를 반환한다.")
        void same_rank() {
            final var sameRankSquare = Square.of(File.B, Rank.TWO);

            assertThat(square.isSameRank(sameRankSquare)).isTrue();
        }

        @Test
        @DisplayName("같은 Rank가 아니면 false를 반환한다.")
        void not_same_rank() {
            final var notSameRankSquare = Square.of(File.B, Rank.FIVE);

            assertThat(square.isSameRank(notSameRankSquare)).isFalse();
        }
    }
}