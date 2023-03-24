package chess.domain;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.File.A;
import static chess.domain.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PieceTest {

    private static final class TestPiece extends Piece {
        public TestPiece(final File file, final Rank rank, final Color color) {
            super(file, rank, color);
        }

        @Override
        protected boolean canMove(final Position targetPosition) {
            return false;
        }

        @Override
        public Piece move(final Piece pieceInTargetPosition) {
            return null;
        }

        @Override
        public List<Position> getPassingPositions(final Position targetPosition) {
            return null;
        }

        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isPawn() {
            return false;
        }

        @Override
        public double getScore() {
            return 0;
        }
    }

    @Test
    void 위치를_가지는_말이_정상적으로_생성이_된다() {
        assertThatNoException().isThrownBy(() -> new TestPiece(A, ONE, BLACK));
    }

    @ParameterizedTest()
    @CsvSource({"BLACK, true", "WHITE, false"})
    void 같은_색인지_확인한다(final Color otherColor, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, BLACK);

        final boolean actual = piece.isSameColor(otherColor);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    void 검정_색인지_확인한다(final Color color, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, color);

        final boolean actual = piece.isBlack();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"BLACK, false", "WHITE, true"})
    void 하얀_색인지_확인한다(final Color color, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, color);

        final boolean actual = piece.isWhite();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("providePathPositions")
    void 입력된_경로에_존재하는지_확인한다(final List<Position> pathPositions, final boolean expected) {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, BLACK);

        final boolean actual = piece.existsIn(pathPositions);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePathPositions() {
        return Stream.of(
                Arguments.of(List.of(new Position(File.C, Rank.EIGHT)), true),
                Arguments.of(List.of(), false)
        );
    }

    @Test
    void 점수를_반환한다() {
        final Piece piece = new TestPiece(File.C, Rank.EIGHT, BLACK);

        final double score = piece.getScore();

        assertThat(score).isEqualTo(0);
    }
}
