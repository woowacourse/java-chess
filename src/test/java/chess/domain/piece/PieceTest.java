package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.feature.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Piece.NO_COLOR_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PieceTest {
    @DisplayName("blank를 제외한 말들에 no color를 주입했을 때 에러를 반환 하는지")
    @Test
    void validateColor_noColor_throwError() {
        assertThatThrownBy(() -> new Bishop(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
        assertThatThrownBy(() -> new Rook(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
        assertThatThrownBy(() -> new King(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
        assertThatThrownBy(() -> new Knight(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
        assertThatThrownBy(() -> new Pawn(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
        assertThatThrownBy(() -> new Queen(Color.NO_COLOR, Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_COLOR_ERROR);
    }
}
