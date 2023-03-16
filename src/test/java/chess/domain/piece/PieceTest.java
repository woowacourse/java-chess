package chess.domain.piece;

import chess.domain.camp.CampType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("두 개의 체스말이 동일한 진영에 속하는지 판단한다.")
    void isSameCamp() {
        // given
        final Piece piece = new Piece(PieceType.PAWN, CampType.WHITE);
        final Piece other = new Piece(PieceType.QUEEN, CampType.WHITE);
        final Piece otherCamp = new Piece(PieceType.KING, CampType.BLACK);

        // when, then
        assertThat(piece.isSameCamp(other))
                .isTrue();

        assertThat(piece.isSameCamp(otherCamp))
                .isFalse();
    }
    
    @ParameterizedTest(name = "체스말이 흰색 진영의 말이면 true, 아니면 false를 반환한다.")
    @MethodSource(value = "makePiece")
    void isBlackCamp(final Piece piece, final boolean expected) {
        assertThat(piece.isBlackCamp())
                .isEqualTo(expected);
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

    private static Stream<Arguments> makePiece() {
        return Stream.of(
                Arguments.of(new Piece(PieceType.QUEEN, CampType.WHITE), true)
                , Arguments.of(new Piece(PieceType.PAWN, CampType.BLACK), false)
        );
    }
}
