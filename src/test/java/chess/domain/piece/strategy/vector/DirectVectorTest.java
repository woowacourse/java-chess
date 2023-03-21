package chess.domain.piece.strategy.vector;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.strategy.vector.DirectVector.EAST;
import static chess.domain.piece.strategy.vector.DirectVector.NORTH;
import static chess.domain.piece.strategy.vector.DirectVector.NORTH_EAST;
import static chess.domain.piece.strategy.vector.DirectVector.NORTH_WEST;
import static chess.domain.piece.strategy.vector.DirectVector.SOUTH;
import static chess.domain.piece.strategy.vector.DirectVector.SOUTH_EAST;
import static chess.domain.piece.strategy.vector.DirectVector.SOUTH_WEST;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_EAST_ONE_NORTH;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_EAST_ONE_SOUTH;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_NORTH;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_NORTH_ONE_EAST;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_NORTH_ONE_WEST;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_SOUTH;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_SOUTH_ONE_EAST;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_SOUTH_ONE_WEST;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_WEST_ONE_NORTH;
import static chess.domain.piece.strategy.vector.DirectVector.TWO_WEST_ONE_SOUTH;
import static chess.domain.piece.strategy.vector.DirectVector.WEST;
import static chess.domain.piece.strategy.vector.DirectVector.ofKing;
import static chess.domain.piece.strategy.vector.DirectVector.ofKnight;
import static chess.domain.piece.strategy.vector.DirectVector.ofPawnByColor;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DirectVectorTest {

    @Nested
    class ofPawnByColor_메서드는 {

        @Test
        void 검은색일때_검은색에_해당하는_벡터들을_반환한다() {
            final List<DirectVector> vectors = ofPawnByColor(BLACK);

            assertThat(vectors).containsExactly(SOUTH, TWO_SOUTH, SOUTH_WEST, SOUTH_EAST);
        }

        @Test
        void 흰색일때_흰색에_해당하는_벡터들을_반환한다() {
            final List<DirectVector> vectors = ofPawnByColor(WHITE);

            assertThat(vectors).containsExactly(NORTH, TWO_NORTH, NORTH_WEST, NORTH_EAST);
        }
    }

    @Test
    void 킹에_해당하는_벡터들을_반환한다() {
        final List<DirectVector> vectors = ofKing();

        assertThat(vectors).containsExactly(NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    @Test
    void 나이트에_해당하는_벡터들을_반환한다() {
        final List<DirectVector> vectors = ofKnight();

        assertThat(vectors).containsExactly(
                TWO_NORTH_ONE_WEST, TWO_NORTH_ONE_EAST, TWO_EAST_ONE_NORTH, TWO_EAST_ONE_SOUTH,
                TWO_SOUTH_ONE_EAST, TWO_SOUTH_ONE_WEST, TWO_WEST_ONE_NORTH, TWO_WEST_ONE_SOUTH
        );
    }

    @Nested
    class isMovable_메서드는 {

        @Test
        void 움직일_수_있다고_판단하면_true_반환한다() {
            assertThat(TWO_EAST_ONE_NORTH.isMovable(2, 1)).isTrue();
        }

        @Test
        void 움직일_수_없다고_판단하면_false_반환한다() {
            assertThat(TWO_EAST_ONE_NORTH.isMovable(1, 1)).isFalse();
        }
    }
}
