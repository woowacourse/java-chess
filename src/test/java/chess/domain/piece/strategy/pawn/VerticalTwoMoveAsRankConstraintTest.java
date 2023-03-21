package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("VerticalTwoMoveAsRankConstraint 은")
class VerticalTwoMoveAsRankConstraintTest {

    private final Rank rank = Rank.from(4);
    private final PawnMoveConstraint constraint = new VerticalTwoMoveAsRankConstraint(rank);

    @Test
    void 수직_두_칸_이동이_아닌_경우_항상_유효하다() {
        // when & then
        assertDoesNotThrow(() -> constraint.validateConstraint(PiecePosition.of("d2"), PiecePosition.of("d3")));
    }

    @Test
    void 수직_두_칸_이동인_경우_Rank_가_일치하면_유효하다() {
        // when & then
        assertDoesNotThrow(() -> constraint.validateConstraint(PiecePosition.of("d4"), PiecePosition.of("d6")));
    }

    @Test
    void 수직_두_칸_이동인_경우_Rank_가_일치하지_않으면_유효하지_않다() {
        // when & then
        assertThatThrownBy(() -> constraint.validateConstraint(PiecePosition.of("d3"), PiecePosition.of("d5")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
