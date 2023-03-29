package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PieceTest {

    @ParameterizedTest(name = "색을 입력받아 다른 색인지 확인한다. 현재 색: WHITE, 입력: {0}, 결과: {1}")
    @CsvSource({"WHITE, false", "BLACK, true"})
    void 색을_입력받아_다른_색인지_확인한다(final Color color, final boolean result) {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // expect
        assertThat(piece.isNotSameColor(color)).isEqualTo(result);
    }


    @ParameterizedTest(name = "색을 입력받아 같은 색인지 확인한다. 현재 색: WHITE, 입력: {0}, 결과: {1}")
    @CsvSource({"WHITE, true", "BLACK, false"})
    void 색을_입력받아_같은_색인지_확인한다(final Color color, final boolean result) {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // expect
        assertThat(piece.isSameColor(color)).isEqualTo(result);
    }

    @ParameterizedTest(name = "색을 입력받아 같은 색인지 확인한다. 현재 타입: PAWN, 입력: {0}, 결과: {1}")
    @CsvSource({"PAWN, true", "KING, false"})
    void 타입을_입력받아_같은_타입인지_확인한다(final PieceType type, final boolean result) {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // expect
        assertThat(piece.isSameType(type)).isEqualTo(result);
    }

    @Test
    void 해당_기물의_색상을_반환한다() {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // when
        final Color color = piece.color();

        // then
        assertThat(color).isEqualTo(Color.WHITE);
    }

    private static Piece generatePiece(final Color color, final PieceType type) {
        final Piece piece = new Piece(color, type) {
            @Override
            protected boolean isValidMove(final Position start, final Position end) {
                return false;
            }

            @Override
            protected boolean isValidTarget(final Piece target) {
                return false;
            }
        };
        return piece;
    }

    @Test
    void 해당_기물의_타입을_반환한다() {
        // given
        final Piece piece = generatePiece(Color.WHITE, PieceType.PAWN);

        // when
        final PieceType type = piece.type();

        // then
        assertThat(type).isEqualTo(PieceType.PAWN);
    }
}
