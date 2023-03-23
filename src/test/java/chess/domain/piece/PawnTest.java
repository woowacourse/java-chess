package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.*;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PawnTest {

    @Nested
    class BlackPawnTest {

        @Test
        void 초기_위치에서_두_칸을_전진할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, SEVEN, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(B, Rank.FIVE));

            assertThat(path).containsExactly(new Position(B, SIX));
        }

        @Test
        void 초기_위치가_아닐_때_한_칸을_전진할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(B, Rank.FIVE));

            assertThat(path).isEmpty();
        }

        @Test
        void 초기_위치가_아닐_때_대각선_한_칸을_이동할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(C, Rank.FIVE));

            assertThat(path).isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, SEVEN", "B, EIGHT"})
        void 초기_위치에서_이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B, SEVEN, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @ParameterizedTest
        @CsvSource({"B, FOUR", "C, SIX", "B, SEVEN"})
        void 초기_위치가_아닐_때_이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }

    @Nested
    class WhitePawnTest {

        @Test
        void 초기_위치에서_두_칸을_전진할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, TWO, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(B, FOUR));

            assertThat(path).containsExactly(new Position(B, THREE));
        }

        @Test
        void 초기_위치가_아닐_때_한_칸을_전진할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(B, FOUR));

            assertThat(path).isEmpty();
        }

        @Test
        void 초기_위치가_아닐_때_대각선_한_칸을_이동할_경우_지나갈_경로를_얻는다() {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(C, FOUR));

            assertThat(path).isEmpty();
        }

        @ParameterizedTest
        @CsvSource({"E, SIX", "C, TWO", "B, ONE"})
        void 초기_위치에서_이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B, TWO, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @ParameterizedTest
        @CsvSource({"B, FIVE", "C, THREE", "B, TWO"})
        void 초기_위치가_아닐_때_이동할_수_없는_위치가_입력되면_예외가_발생한다(final File file, final Rank rank) {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(file, rank)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }

    @ParameterizedTest
    @MethodSource("providePieceInTargetPosition")
    void 말을_이동시킨다(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(A, SIX, BLACK);

        final Piece movedRook = originalPawn.move(pieceInTargetPosition);

        assertThat(movedRook.getPosition()).isEqualTo(pieceInTargetPosition.getPosition());
    }

    private static Stream<Arguments> providePieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(A, FIVE)),
                Arguments.of(new Pawn(B, FIVE, WHITE))
        );
    }

    @ParameterizedTest
    @CsvSource("WHITE, BLACK")
    void 목표_위치에_같은_색_말이_있다면_예외가_발생한다(final Color color) {
        final Piece originalPawn = new Pawn(A, SIX, BLACK);
        final Piece sameColorPiece = new Pawn(A, FIVE, color);

        assertThatThrownBy(() -> originalPawn.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideDiagonalPieceInTargetPosition")
    void 대각선_위치에_같은색_말이_있거나_아무_말도_없으면_예외를_발생한다(final Piece pieceInTargetPosition) {
        final Piece originalPawn = new Pawn(A, SIX, BLACK);

        assertThatThrownBy(() -> originalPawn.move(pieceInTargetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> provideDiagonalPieceInTargetPosition() {
        return Stream.of(
                Arguments.of(new BlankPiece(B, FIVE)),
                Arguments.of(new Pawn(B, FIVE, BLACK))
        );
    }
}
