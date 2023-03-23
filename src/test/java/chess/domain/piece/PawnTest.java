package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("Black 폰이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @Test
    void canMoveBlack() {
        // given
        final Piece piece = new BlackPawn(PieceType.PAWN);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(0, 1), null);

        // then
        assertThat(actual)
                .isTrue();
    }

    @DisplayName("White 폰이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @Test
    void canMoveWhite() {
        // given
        final Piece piece = new WhitePawn(PieceType.PAWN);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(2, 1), null);

        // then
        assertThat(actual)
                .isTrue();
    }

    @DisplayName("Black 폰이 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @Test
    void canMoveFailBlack() {
        // given
        final Piece piece = new BlackPawn(PieceType.PAWN);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(2, 1), null);

        // then
        assertThat(actual)
                .isFalse();
    }

    @DisplayName("White 폰이 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @Test
    void canMoveFailWhite() {
        // given
        final Piece piece = new WhitePawn(PieceType.PAWN);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(0, 1), null);

        // then
        assertThat(actual)
                .isFalse();
    }
}
