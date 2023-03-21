package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.BlankPiece.BLANK_PIECE_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlankPieceTest {

    private static final BlankPiece blankPiece = new BlankPiece(Position.of(File.A, Rank.ONE));

    @Test
    @DisplayName("생성시 Blank 색상 정보를 가진다")
    void default_color_is_blank_test() {
        final Color actualColor = blankPiece.getColor();

        assertThat(actualColor).isEqualTo(Color.BLANK);
    }

    @Test
    @DisplayName("이동 명령시 예외를 발생한다")
    void move_throws_exception() {
        assertThatThrownBy(() -> blankPiece.move(new BlankPiece(Position.of(File.A, Rank.TWO))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BLANK_PIECE_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("이동 경로 반환 명령시 예외를 발생한다")
    void get_passing_positions_throws_exception() {
        assertThatThrownBy(() -> blankPiece.getPassingPositions(Position.of(File.A, Rank.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BLANK_PIECE_EXCEPTION_MESSAGE);
    }
}
