package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("나이트는 이동전략 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Knight knight = new Knight(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.F, Rank.ONE)); // 유효하지 않은 이동 전략

        // when && then
        assertThatThrownBy(() -> knight.getRoute(movement))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 전략상 이동할 수 없는 위치입니다.");
    }

}
