package chess.domain.piece;

import chess.domain.chess.CampType;
import chess.domain.piece.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

    @Test
    @DisplayName("두 체스말이 동일한 진영인지 확인한다.")
    void isSameCamp_withPiece() {
        // given
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());
        final Piece king = new Piece(PieceType.KING, CampType.BLACK, new King());
        final Piece queen = new Piece(PieceType.QUEEN, CampType.WHITE, new Queen());

        // when
        final boolean actualFalse = pawn.isSameCamp(king);
        final boolean actualTrue = pawn.isSameCamp(queen);

        // then
        assertThat(actualFalse)
                .isFalse();

        assertThat(actualTrue)
                .isTrue();
    }

    @Test
    @DisplayName("체스말이 입력받은 진영에 속하는지 판단한다.")
    void isSameCamp_withCampType() {
        // given
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when
        boolean actualTrue = pawn.isSameCamp(CampType.WHITE);
        boolean actualFalse = pawn.isSameCamp(CampType.BLACK);

        // then
        assertThat(actualTrue)
                .isTrue();

        assertThat(actualFalse)
                .isFalse();
    }

    @ParameterizedTest(name = "폰은 처음에 기물이 없는 곳으로 최대 2칸 이동할 수 있다.")
    @CsvSource(value = {"2:false:true", "3:false:true", "4:false:false",
            "2:true:false", "3:true:false", "4:true:false"}, delimiter = ':')
    void pawn_canMove(final int targetRank, final boolean isTargetExist, final boolean expected) {
        // given
        final Position source = new Position(1, 0);
        final Position target = new Position(targetRank, 0);
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when
        boolean actual = pawn.canMove(source, target, isTargetExist);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @Test
    @DisplayName("폰은 처음 이동이 아니면 1칸만 이동이 가능하다.")
    void pawn_canMoveFail() {
        // given
        final Position source = new Position(2, 0);
        final Position target = new Position(4, 0);
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when, then
        assertThatThrownBy(() -> pawn.canMove(source, target, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 처음 이후 1칸만 전진할 수 있습니다.");
    }

    @Test
    @DisplayName("폰은 공격 가능한 위치에 상대 기물이 존재하지 않는 경우 예외가 발생한다.")
    void pawn_canAttackFail() {
        // given
        final Position source = new Position(1, 1);
        final Position target = new Position(2, 2);
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when, then
        assertThatThrownBy(() -> pawn.canAttack(source, target, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 공격할 수 있는 위치가 아닙니다.");
    }

    @Test
    @DisplayName("폰은 공격 가능한 위치에 상대 기물이 존재하는 경우 공격할 수 있다.")
    void pawn_canAttack() {
        // given
        final Position source = new Position(1, 1);
        final Position target = new Position(2, 2);
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when
        boolean actual = pawn.canAttack(source, target, true);

        // then
        assertThat(actual)
                .isTrue();
    }

    @Test
    @DisplayName("체스말이 킹인지 판단한다.")
    void isKing() {
        // given
        final Piece king = new Piece(PieceType.KING, CampType.WHITE, new King());
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when, then
        assertThat(king.isKing())
                .isTrue();

        assertThat(pawn.isKing())
                .isFalse();
    }

    @Test
    @DisplayName("체스말이 폰인지 판단한다.")
    void isPawn() {
        // given
        final Piece king = new Piece(PieceType.KING, CampType.WHITE, new King());
        final Piece pawn = new Piece(PieceType.PAWN, CampType.WHITE, new Pawn());

        // when, then
        assertThat(pawn.isPawn())
                .isTrue();

        assertThat(king.isPawn())
                .isFalse();
    }
}
