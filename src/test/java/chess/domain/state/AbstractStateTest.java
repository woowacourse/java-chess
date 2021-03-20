package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractStateTest {

    @Test
    @DisplayName("컬러가 같은지 테스트")
    void isSameColor() {
        Piece piece = new Queen(Color.WHITE, Position.of("a1"));
        assertThat(new Play().isSameColor(piece)).isTrue();

        piece = new Queen(Color.BLACK, Position.of("a1"));
        assertThat(new Play().isSameColor(piece)).isFalse();
    }
}