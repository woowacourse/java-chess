package chess.domain.piece.strategy;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.domain.piece.strategy.constraint.MoveConstraint;
import chess.domain.piece.strategy.constraint.TwoVerticalMoveAsRankConstraint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TwoVerticalMoveAsRankConstraint 은")
class TwoVerticalMoveAsRankConstraintTest {

    private final Rank standard = Rank.from(4);
    private final MoveConstraint constraint = new TwoVerticalMoveAsRankConstraint(standard);

    @Nested
    @DisplayName("북쪽 혹은 남쪽으로 두 칸 이동하는 경우")
    class TwoVertical {

        @Nested
        class 시작_위치의_Rank_가_전략과_일치한다면 {

            private final PiecePosition source = PiecePosition.of("d4");

            @Test
            void 이동할_수_있다() {
                // given
                final PiecePosition d6 = PiecePosition.of("d6");
                final PiecePosition d2 = PiecePosition.of("d2");
                final Path south = Path.of(source, d2);
                final Path north = Path.of(source, d6);

                // when & then
                assertThat(constraint.satisfy(south)).isTrue();
                assertThat(constraint.satisfy(north)).isTrue();
            }
        }

        @Nested
        class 시작_위치의_Rank_가_전략과_일치하지_않는다면 {

            private final PiecePosition source = PiecePosition.of("d3");

            @Test
            void 이동할_수_없다() {
                // given
                final PiecePosition d5 = PiecePosition.of("d5");
                final PiecePosition d1 = PiecePosition.of("d1");
                final Path south = Path.of(source, d5);
                final Path north = Path.of(source, d1);

                // when & then
                assertThat(constraint.satisfy(south)).isFalse();
                assertThat(constraint.satisfy(north)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("북쪽 혹은 남쪽으로 두 칸 이동하는 경우를 제외하면")
    class ExpectTwoVertical {

        @ParameterizedTest(name = "모두 이동가능하다. ex: 기준 Rank 가 4일때, [{0}] -> [{1}] 이동가능")
        @CsvSource({
                "d4, d5",
                "d4, d3",
                "d4, d7",
                "d4, d1",
                "d3, d4",
                "d3, d2",
                "d3, d6",
                "d3, d7",
        })
        void 모두_이동가능하다(final PiecePosition source, final PiecePosition destination) {
            // given
            final Path path = Path.of(source, destination);

            // when & then
            assertThat(constraint.satisfy(path)).isTrue();
        }
    }
}
