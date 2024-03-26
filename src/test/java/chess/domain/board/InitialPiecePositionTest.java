package chess.domain.board;

import static chess.PositionFixture.BLACK_PAWN_FIRST_MOVE_POSITION;
import static chess.PositionFixture.PAWN_NOT_FIRST_MOVE_POSITION;
import static chess.PositionFixture.WHITE_PAWN_FIRST_MOVE_POSITION;
import static chess.domain.board.InitialPiecePosition.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialPiecePositionTest {

    @Test
    @DisplayName("현재 위치를 받아 해당 피스가 폰의 처음 이동인지 확인한다.")
    void isPawnFirstMove() {
        assertAll(
                () -> assertThat(WHITE_PAWN.isPawnFirstMove(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition())).isTrue(),
                () -> assertThat(WHITE_PAWN.isPawnFirstMove(PAWN_NOT_FIRST_MOVE_POSITION.getPosition())).isFalse(),
                () -> assertThat(WHITE_PAWN.isPawnFirstMove(BLACK_PAWN_FIRST_MOVE_POSITION.getPosition())).isFalse()
        );
    }
}
