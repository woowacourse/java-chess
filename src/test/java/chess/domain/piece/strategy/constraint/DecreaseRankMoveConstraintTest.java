package chess.domain.piece.strategy.constraint;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("DecreaseRankMoveConstraint 은")
class DecreaseRankMoveConstraintTest {

    private final MoveConstraint constraint = new DecreaseRankMoveConstraint();
    private final PiecePosition source = PiecePosition.of("d5");
    private final PiecePosition decreaseRank = PiecePosition.of("d4");
    private final PiecePosition equalRank = PiecePosition.of("c5");
    private final PiecePosition increaseRank = PiecePosition.of("d6");

    @Nested
    class Rank_가_감소하는_이동은 {

        private final Path path = Path.of(source, decreaseRank);

        @Test
        void 허락한다() {
            // when & then
            assertThat(constraint.satisfy(path)).isTrue();
        }
    }

    @Nested
    class Rank_가_변치않는_이동은 {

        private final Path path = Path.of(source, equalRank);

        @Test
        void 불허한다() {
            // when & then
            assertThat(constraint.satisfy(path)).isFalse();
        }
    }

    @Nested
    class Rank_가_증가하는_이동은 {

        private final Path path = Path.of(source, increaseRank);

        @Test
        void 불허한다() {
            // when & then
            assertThat(constraint.satisfy(path)).isFalse();
        }
    }
}
