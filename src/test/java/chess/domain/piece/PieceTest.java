package chess.domain.piece;

import chess.domain.camp.CampType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("두 개의 체스말이 동일한 진영에 속하는지 판단한다.")
    void compareCamp() {
        // given
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE);
        final Piece other = new Piece(PieceType.QUEEN, CampType.WHITE);
        final Piece otherCamp = new Piece(PieceType.KING, CampType.BLACK);

        // when, then
        assertThat(piece.compareCamp(other))
                .isTrue();

        assertThat(piece.compareCamp(otherCamp))
                .isFalse();
    }

    @Test
    @DisplayName("체스말이 폰이면 true를 반환한다.")
    void isPawn() {
        // given
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE);

        // when, then
        assertThat(piece.isPawn())
                .isTrue();
    }

    @Test
    @DisplayName("체스말이 나이트면 true를 반환한다.")
    void isKnight() {
        // given
        final Piece piece = new Piece(PieceType.KNIGHT, CampType.WHITE);

        // when, then
        assertThat(piece.isKnight())
                .isTrue();
    }

    @ParameterizedTest(name = "체스말이 입력받은 체스말과 동일한 진영인지 판단한다.")
    @CsvSource(value = {"WHITE:true", "BLACK:false"}, delimiter = ':')
    void isSameCamp(final CampType campType, final boolean expected) {
        // given
        final Piece piece = new Piece(PieceType.KNIGHT, CampType.WHITE);

        // when, then
        assertThat(piece.isSameCamp(campType))
                .isSameAs(expected);
    }
}
