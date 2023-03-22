package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("두 개의 체스말이 동일한 진영에 속하는지 판단한다.")
    void compareCamp() {
        // given
        final Piece piece = new Pawn(PieceType.PAWN, TeamColor.WHITE);
        final Piece other = new Queen(PieceType.QUEEN, TeamColor.WHITE);
        final Piece otherCamp = new King(PieceType.KING, TeamColor.BLACK);

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
        final Piece piece = new Pawn(PieceType.PAWN, TeamColor.WHITE);

        // when, then
        assertThat(piece.isPawn())
                .isTrue();
    }

    @Test
    @DisplayName("체스말이 나이트면 true를 반환한다.")
    void isKnight() {
        // given
        final Piece piece = new Knight(PieceType.KNIGHT, TeamColor.WHITE);

        // when, then
        assertThat(piece.isKnight())
                .isTrue();
    }

    @ParameterizedTest(name = "체스말이 입력받은 체스말과 동일한 진영인지 판단한다.")
    @CsvSource(value = {"WHITE:true", "BLACK:false"}, delimiter = ':')
    void isSameCamp(final TeamColor campType, final boolean expected) {
        // given
        final Piece piece = new Knight(PieceType.KNIGHT, TeamColor.WHITE);

        // when, then
        assertThat(piece.isSameCamp(campType))
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "특정 체스말이 시작 위치에서 타겟 위치로 이동 가능한지 판단한다.")
    @MethodSource(value = "makePosition")
    void canMove(final Position source, final Position target) {
        // given
        final Piece piece = new Queen(PieceType.QUEEN, TeamColor.WHITE);

        // when
        piece.canMove(source, target);

        // then
        assertThat(piece.canMove(source, target))
                .isTrue();
    }

    private static Stream<Arguments> makePosition() {
        return Stream.of(
                Arguments.of(new Position(1, 1), new Position(7, 7))
                , Arguments.of(new Position(1, 1), new Position(7, 1))
                , Arguments.of(new Position(1, 1), new Position(2, 1))
                , Arguments.of(new Position(1, 1), new Position(0, 0))
                , Arguments.of(new Position(1, 1), new Position(1, 0))
        );
    }
}
