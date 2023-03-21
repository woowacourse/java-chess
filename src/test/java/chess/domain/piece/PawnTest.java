package chess.domain.piece;

import static chess.domain.board.Position.of;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PawnTest {

    @Test
    void 폰이_정상적으로_생성된다() {
        // given
        final Pawn pawn = Pawn.from(Color.WHITE);

        // expect
        assertThat(pawn.type()).isEqualTo(PieceType.PAWN);
    }

    @Nested
    class 흰색_폰이_ {
        // given
        final Pawn pawn = Pawn.from(WHITE);
        final Position start = of(File.E, Rank.TWO);

        @Nested
        class 뒤로는_ {

            @ParameterizedTest(name = "이동할 수 없다. 현재 위치: E, TWO, 이동 위치: {0}, {1}")
            @CsvSource({"D, ONE", "E, ONE", "F, ONE"})
            void 이동할_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }

        @Nested
        class 전진_시_ {

            @ParameterizedTest(name = "기물이 존재하지 않으면 움직일 수 있다. 현재 위치: E, TWO, 이동 위치: {0}, {1}")
            @CsvSource({"E, THREE", "E, FOUR"})
            void 기물이_존재하지_않으면_움직일_수_있다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isTrue();
            }

            @ParameterizedTest(name = "상대방 기물이 존재하면 움직일 수 없다. 현재 위치: E, TWO, 이동 위치: {0}, {1}")
            @CsvSource({"E, THREE", "E, FOUR"})
            void 상대방_기물이_존재하면_움직일_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Pawn.from(BLACK);

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }

        @Nested
        class 대각선_이동_시_ {

            @ParameterizedTest(name = "상대방의 기물이 존재하면 움직일 수 있다. 현재 위치: E, TWO, 이동 위치: {0}, {1}")
            @CsvSource({"F, THREE", "D, THREE"})
            void 상대방의_기물이_존재하면_움직일_수_있다(final File file, final Rank rank) {
                // when
                final Piece target = Pawn.from(BLACK);

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isTrue();
            }

            @ParameterizedTest(name = "상대방의 기물이 존재하지 않으면 움직일 수 없다. 현재 위치: E, TWO, 이동 위치: {0}, {1}")
            @CsvSource({"F, THREE", "D, THREE"})
            void 상대방의_기물이_존재하지_않으면_움직일_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }
    }

    @Nested
    class 검은색_폰이_ {
        // given
        final Pawn pawn = Pawn.from(BLACK);
        final Position start = of(File.E, Rank.SEVEN);

        @Nested
        class 뒤로는_ {

            @ParameterizedTest(name = "이동할 수 없다. 현재 위치: E, SEVEN, 이동 위치: {0}, {1}")
            @CsvSource({"D, EIGHT", "E, EIGHT", "F, EIGHT"})
            void 이동할_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }

        @Nested
        class 전진_시_ {

            @ParameterizedTest(name = "기물이 존재하지 않으면 움직일 수 있다. 현재 위치: E, SEVEN, 이동 위치: {0}, {1}")
            @CsvSource({"E, SIX", "E, FIVE"})
            void 기물이_존재하지_않으면_움직일_수_있다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isTrue();
            }

            @ParameterizedTest(name = "상대방 기물이 존재하면 움직일 수 없다. 현재 위치: E, SEVEN, 이동 위치: {0}, {1}")
            @CsvSource({"E, SIX", "E, FIVE"})
            void 상대방_기물이_존재하면_움직일_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Pawn.from(WHITE);

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }

        @Nested
        class 대각선_이동_시_ {

            @ParameterizedTest(name = "상대방의 기물이 존재하면 움직일 수 있다. 현재 위치: E, SEVEN, 이동 위치: {0}, {1}")
            @CsvSource({"D, SIX", "F, SIX"})
            void 상대방의_기물이_존재하면_움직일_수_있다(final File file, final Rank rank) {
                // when
                final Piece target = Pawn.from(WHITE);

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isTrue();
            }

            @ParameterizedTest(name = "상대방의 기물이 존재하지 않으면 움직일 수 없다. 현재 위치: E, SEVEN, 이동 위치: {0}, {1}")
            @CsvSource({"D, SIX", "F, SIX"})
            void 상대방의_기물이_존재하지_않으면_움직일_수_없다(final File file, final Rank rank) {
                // when
                final Piece target = Empty.getInstance();

                // then
                assertThat(pawn.isMovable(start, of(file, rank), target)).isFalse();
            }
        }
    }
}
