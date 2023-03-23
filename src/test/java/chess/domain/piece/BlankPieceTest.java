package chess.domain.piece;

import chess.constant.ExceptionCode;
import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.A1;
import static chess.PositionFixture.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlankPieceTest {

    private static final BlankPiece blankPiece = BlankPiece.of(A1);

    @Test
    @DisplayName("생성시 Blank 색상 정보를 가진다")
    void default_color_is_blank_test() {
        final Color actualColor = blankPiece.getColor();

        assertThat(actualColor).isEqualTo(Color.BLANK);
    }

    @Test
    @DisplayName("이동 명령시 예외를 발생한다")
    void move_throws_exception() {
        assertThatThrownBy(() -> blankPiece.move(BlankPiece.of(A2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.ACCESS_BLANK_PIECE.name());
    }

    @Test
    @DisplayName("이동 경로 반환 명령시 예외를 발생한다")
    void get_passing_positions_throws_exception() {
        assertThatThrownBy(() -> blankPiece.getPassingPositions(A2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.ACCESS_BLANK_PIECE.name());
    }
}
